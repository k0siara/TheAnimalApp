package com.patrykkosieradzki.thecatapp.utils

import android.content.Intent
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.test.espresso.intent.Intents
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.hadilq.liveevent.LiveEvent
import com.nhaarman.mockitokotlin2.mock
import com.patrykkosieradzki.thecatapp.R
import com.patrykkosieradzki.thecatapp.ui.utils.BaseFragment
import com.patrykkosieradzki.thecatapp.ui.utils.BaseViewModel
import com.patrykkosieradzki.thecatapp.ui.utils.ViewState
import org.koin.test.KoinTest
import org.koin.test.mock.MockProviderRule
import org.mockito.Answers
import org.mockito.Mockito
import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.Answer

fun fragmentTestRuleWithMocks(usesIntents: Boolean = false, stubbing: () -> Unit = {}) =
    object : FragmentTestRule(usesIntents) {
        override fun beforeActivityLaunched() {
            super.beforeActivityLaunched()
            stubbing()
        }
    }

open class FragmentTestRule(usesIntents: Boolean) :
    BaseFragmentTestRule<ActivityForTestingFragment>(
        usesIntents,
        ActivityForTestingFragment::class.java
    )

abstract class FragmentTest<
        STATE : ViewState,
        VM : BaseViewModel<STATE>,
        R : FragmentRobot<STATE, VM>
        > : RobotTest<R>()

open class FragmentRobot<
        STATE : ViewState,
        VM : BaseViewModel<STATE>
        >(rule: FragmentTestRule) : BaseFragmentRobot<ActivityForTestingFragment>(
    rule,
    ActivityForTestingFragment::class.java,
    R.id.test_fragment
) {

    fun setViewState(state: STATE) {
        onViewModel {
            val viewState = viewState as MutableLiveData
            viewState.value = state
        }
    }

    fun onViewModel(action: VM.() -> Unit) {
        rule.activity.runOnUiThread {
            @Suppress("UNCHECKED_CAST")
            val fragment = rule.activity.supportFragmentManager.findFragmentById(R.id.test_fragment)
                    as? BaseFragment<STATE, VM, ViewDataBinding>
            fragment?.let {
                it.viewModel.action()
            }
        }
    }
}

open class BaseFragmentTestRule<T : AppCompatActivity>(
    private val usesIntents: Boolean,
    testActivity: Class<T>
) : ActivityTestRule<T>(
    testActivity, true, false
), KoinTest {
    private var isInitialized: Boolean = false

    override fun afterActivityLaunched() {
        if (usesIntents) {
            Intents.init()
            isInitialized = true
        }
        super.afterActivityLaunched()
    }

    override fun afterActivityFinished() {
        super.afterActivityFinished()
        if (usesIntents) {
            if (isInitialized) {
                Intents.release()
                isInitialized = false
            }
        }
    }
}

open class BaseFragmentRobot<T : AppCompatActivity>(
    rule: BaseFragmentTestRule<T>,
    private val testActivity: Class<T>,
    private val fragmentContainerId: Int
) : ActivityRobot<T>(rule) {

    fun startFragmentWithNav(
        @IdRes destinationId: Int,
        @NavigationRes navigationId: Int
    ) {
        rule.launchActivity(
            Intent(
                InstrumentationRegistry.getInstrumentation().targetContext,
                testActivity
            )
        )
        with(rule.activity) {
            runOnUiThread {
                with(findNavController(fragmentContainerId)) {
                    val graphToSet = navInflater.inflate(navigationId)
                    graphToSet.startDestination = destinationId
                    setGraph(graphToSet, intent.extras)
                }
            }
        }
    }

    fun start() {
        rule.launchActivity(
            Intent(
                InstrumentationRegistry.getInstrumentation().targetContext,
                testActivity
            )
        )
    }

    fun startWithDestination(destinationId: Int) {
        with(rule.activity) {
            with(findNavController(fragmentContainerId)) {
                this.navigate(destinationId)
            }
        }
    }

    fun startFragment(
        fragment: Fragment
    ) {
        val intent = Intent(
            InstrumentationRegistry.getInstrumentation().targetContext,
            testActivity
        )
        rule.launchActivity(intent)
        with(rule.activity) {
            runOnUiThread {
                supportFragmentManager.beginTransaction()
                    .add(fragmentContainerId, fragment)
                    .commitNow()
            }
        }
    }
}

inline fun <reified VM : ViewModel, reified VS : ViewState> mockViewModelRule(
    defaultViewState: VS
): MockProviderRule = MockProviderRule.create { clazz ->
    if (clazz.isSubclassOf(ViewModel::class)) {
        mock<VM>(defaultAnswer = MyDefaultAnswer(defaultViewState))
    } else {
        Mockito.mock(clazz.java)
    }
}

@Suppress("ReturnCount")
class MyDefaultAnswer(defaultViewState: ViewState) : Answer<Any> {

    private val liveDataObjects = mutableMapOf<String, LiveEvent<Any>>()
    private val viewState = MutableLiveData(defaultViewState)

    override fun answer(invocation: InvocationOnMock?): Any? {
        return when (invocation!!.method.returnType) {
            LiveEvent::class.java -> {
                val key = invocation.method.name
                if (!liveDataObjects.containsKey(key)) {
                    liveDataObjects[key] = LiveEvent()
                }
                liveDataObjects[key]
            }
            LiveData::class.java -> {
                if (invocation.method.name == "getViewState") {
                    viewState
                } else {
                    val generic = invocation.method.genericReturnType
                    if (generic.toString() == "androidx.lifecycle.LiveData<java.lang.Boolean>") {
                        MutableLiveData(false)
                    } else {
                        Answers.RETURNS_DEFAULTS.answer(invocation)
                    }
                }
            }
            else -> Answers.RETURNS_DEFAULTS.answer(invocation)
        }
    }
}
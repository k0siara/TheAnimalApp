package com.patrykkosieradzki.theanimalapp.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent
import com.nhaarman.mockitokotlin2.mock
import kotlin.reflect.full.isSubclassOf
import org.koin.test.mock.MockProviderRule
import org.mockito.Answers
import org.mockito.Mockito
import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.Answer

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

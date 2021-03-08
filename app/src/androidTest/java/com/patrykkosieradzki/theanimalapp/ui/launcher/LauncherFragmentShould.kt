package com.patrykkosieradzki.theanimalapp.ui.launcher

import com.patrykkosieradzki.theanimalapp.utils.CATS
import com.patrykkosieradzki.theanimalapp.utils.FlavorTest
import com.patrykkosieradzki.theanimalapp.utils.FragmentRobot
import com.patrykkosieradzki.theanimalapp.utils.FragmentTestRule
import com.patrykkosieradzki.theanimalapp.utils.RobotTest
import com.patrykkosieradzki.theanimalapp.utils.fragmentTestRuleWithMocks
import com.patrykkosieradzki.theanimalapp.utils.mockViewModelRule
import org.junit.Rule
import org.junit.Test
import org.koin.test.mock.declareMock

class LauncherFragmentShould : RobotTest<LauncherFragmentRobot>() {

    @get:Rule
    val mockRule = mockViewModelRule<LauncherViewModel, LauncherViewState>(
        defaultViewState = LauncherViewState(
            inProgress = false
        )
    )

    @get:Rule
    val rule = fragmentTestRuleWithMocks {
        declareMock<LauncherViewModel>()
    }

    @Test
    @FlavorTest(flavors = [CATS])
    fun showCatLauncherScreen() {
        withRobot {
            startFragment(LauncherFragment())
            wait(1)
            capture("01_Launcher_Screen")
        }
    }

    override fun createRobot() = LauncherFragmentRobot(rule)
}

class LauncherFragmentRobot(
    rule: FragmentTestRule
) : FragmentRobot<LauncherViewState, LauncherViewModel>(rule)

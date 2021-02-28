package com.patrykkosieradzki.thecatapp.ui.randomcat

import com.patrykkosieradzki.thecatapp.utils.*
import org.junit.Rule
import org.junit.Test
import org.koin.test.mock.declareMock

class RandomCatFragmentShould : RobotTest<RandomCatFragmentRobot>() {

    @get:Rule
    val mockRule = mockViewModelRule<RandomCatViewModel, RandomCatViewState>(
        defaultViewState = RandomCatViewState(inProgress = false)
    )

    @get:Rule
    val rule = fragmentTestRuleWithMocks{
        declareMock<RandomCatViewModel>()
    }

    @Test
    fun showLoadingOfRandomCat() {
        withRobot {
            startFragment(RandomCatFragment())
            wait(1)
            capture("01_Random_Cat_Screen_Loading")
        }
    }

    override fun createRobot() = RandomCatFragmentRobot(rule)
}

class RandomCatFragmentRobot(
    rule: FragmentTestRule
) : FragmentRobot<RandomCatViewState, RandomCatViewModel>(rule)
package com.patrykkosieradzki.theanimalapp.ui.randomanimal

import com.patrykkosieradzki.theanimalapp.utils.*
import org.junit.Rule
import org.junit.Test
import org.koin.test.mock.declareMock

class RandomAnimalFragmentShould : RobotTest<RandomCatFragmentRobot>() {

    @get:Rule
    val mockRule = mockViewModelRule<RandomAnimalViewModel, RandomAnimalViewState>(
        defaultViewState = RandomAnimalViewState(
            inProgress = false
        )
    )

    @get:Rule
    val rule = fragmentTestRuleWithMocks{
        declareMock<RandomAnimalViewModel>()
    }

    @Test
    @TestForFlavors(flavors = [CATS])
    fun showLoadingOfRandomCat() {
        withRobot {
            startFragment(RandomAnimalFragment())
            wait(1)
            capture("01_Random_Cat_Screen_Loading")
        }
    }

    override fun createRobot() = RandomCatFragmentRobot(rule)
}

class RandomCatFragmentRobot(
    rule: FragmentTestRule
) : FragmentRobot<RandomAnimalViewState, RandomAnimalViewModel>(rule)
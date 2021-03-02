package com.patrykkosieradzki.theanimalapp.ui.randomanimal

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

class RandomAnimalFragmentShould : RobotTest<RandomCatFragmentRobot>() {

    @get:Rule
    val mockRule = mockViewModelRule<RandomAnimalViewModel, RandomAnimalViewState>(
        defaultViewState = RandomAnimalViewState(
            inProgress = false
        )
    )

    @get:Rule
    val rule = fragmentTestRuleWithMocks {
        declareMock<RandomAnimalViewModel>()
    }

    @Test
    @FlavorTest(flavors = [CATS])
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

package com.patrykkosieradzki.theanimalapp.ui.maintenance

import com.patrykkosieradzki.theanimalapp.utils.CATS
import com.patrykkosieradzki.theanimalapp.utils.FlavorTest
import com.patrykkosieradzki.theanimalapp.utils.FragmentScenarioRobot
import com.patrykkosieradzki.theanimalapp.utils.RobotTest
import com.patrykkosieradzki.theanimalapp.utils.declareMocksTestRule
import com.patrykkosieradzki.theanimalapp.utils.mockViewModelRule
import org.junit.Rule
import org.junit.Test
import org.koin.test.mock.declareMock

class MaintenanceFragmentShould : RobotTest<MaintenanceFragmentRobot>() {

    @get:Rule
    val mockRule = mockViewModelRule<MaintenanceViewModel, MaintenanceViewState>(
        defaultViewState = MaintenanceViewState(
            inProgress = false
        )
    )

    @get:Rule
    val rule = declareMocksTestRule {
        declareMock<MaintenanceViewModel>()
    }

    @Test
    @FlavorTest(flavors = [CATS])
    fun showCatsMaintenanceScreen() {
        withRobot {
            startFragment()
            setViewState(
                MaintenanceViewState(
                    inProgress = false,
                    title = "Maintenance mode",
                    description = "Please, come back later :)"
                )
            )
            wait(1)
            capture("02_Maintenance_Screen")
        }
    }

    override fun createRobot() = MaintenanceFragmentRobot()
}

class MaintenanceFragmentRobot :
    FragmentScenarioRobot<MaintenanceViewState, MaintenanceViewModel>() {

    fun startFragment() {
        startFragment { MaintenanceFragment() }
    }
}

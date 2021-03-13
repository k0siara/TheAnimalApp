package com.patrykkosieradzki.theanimalapp.utils

import android.os.Bundle
import androidx.navigation.NavDirections

sealed class NavigationCommand {
    data class To(val directions: NavDirections) : NavigationCommand()
    object Back : NavigationCommand()
    data class BackWithArgs(val bundle: Bundle) : NavigationCommand()
}

package com.patrykkosieradzki.theanimalapp.utils

import android.os.Bundle

interface BackNavigationListener {
    fun onNavigationResult(result: BackNavigationResult)
}

data class BackNavigationResult(val bundle: Bundle)
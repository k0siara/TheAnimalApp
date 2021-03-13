package com.patrykkosieradzki.theanimalapp.ui.settings

import android.os.Bundle
import com.patrykkosieradzki.theanimalapp.R
import com.patrykkosieradzki.theanimalapp.databinding.SettingsFragmentBinding
import com.patrykkosieradzki.theanimalapp.utils.BasePreferenceFragment

class SettingsFragment :
    BasePreferenceFragment<SettingsViewState, SettingsViewModel, SettingsFragmentBinding>(
        SettingsViewModel::class
    ) {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }
}

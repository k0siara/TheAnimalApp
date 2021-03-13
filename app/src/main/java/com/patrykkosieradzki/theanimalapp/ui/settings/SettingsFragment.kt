package com.patrykkosieradzki.theanimalapp.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.patrykkosieradzki.theanimalapp.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }

}
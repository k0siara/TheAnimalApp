package com.patrykkosieradzki.theanimalapp.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import com.google.android.material.appbar.MaterialToolbar
import com.patrykkosieradzki.theanimalapp.R
import com.patrykkosieradzki.theanimalapp.databinding.SettingsFragmentBinding
import com.patrykkosieradzki.theanimalapp.security.EncryptedPreferenceDataStore
import com.patrykkosieradzki.theanimalapp.utils.BasePreferenceFragment
import org.koin.android.ext.android.inject

class SettingsFragment :
    BasePreferenceFragment<SettingsViewState, SettingsViewModel, SettingsFragmentBinding>(
        SettingsViewModel::class
    ) {

    private val sharedPreferences: SharedPreferences by inject()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        preferenceManager.preferenceDataStore = EncryptedPreferenceDataStore(sharedPreferences)
        setPreferencesFromResource(R.xml.settings, rootKey)
    }

    override fun setupViews(view: View) {
        view.findViewById<MaterialToolbar>(R.id.toolbar)?.setNavigationOnClickListener {
            onBackEvent.invoke()
        }
    }
}

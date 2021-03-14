package com.patrykkosieradzki.theanimalapp.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.appbar.MaterialToolbar
import com.patrykkosieradzki.theanimalapp.R
import com.patrykkosieradzki.theanimalapp.databinding.SettingsFragmentBinding
import com.patrykkosieradzki.theanimalapp.security.EncryptedPreferenceDataStore
import com.patrykkosieradzki.theanimalapp.utils.BasePreferenceFragment
import org.koin.android.ext.android.inject

class SettingsFragment :
    BasePreferenceFragment<SettingsViewState, SettingsViewModel, SettingsFragmentBinding>(
        SettingsViewModel::class
    ),
    SharedPreferences.OnSharedPreferenceChangeListener {

    private val sharedPreferences: SharedPreferences by inject()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        preferenceManager.preferenceDataStore = EncryptedPreferenceDataStore(sharedPreferences)
        setPreferencesFromResource(R.xml.settings, rootKey)
    }

    override fun onResume() {
        super.onResume()
        sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun setupViews(view: View) {
        view.findViewById<MaterialToolbar>(R.id.toolbar)?.setNavigationOnClickListener {
            onBackEvent.invoke()
        }
    }

    override fun onPause() {
        super.onPause()
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }
}

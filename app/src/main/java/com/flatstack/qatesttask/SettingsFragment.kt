package com.flatstack.qatesttask

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.flatstack.qatesttask.data.guardiannews.model.Language
import com.flatstack.qatesttask.feature.viewmodel.SettingsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : PreferenceFragmentCompat() {
    private val viewModel: SettingsViewModel by viewModel()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.main_preferences, rootKey)
        viewModel.setDarkModeChangeCallback {
            AppCompatDelegate.setDefaultNightMode(
                if (it) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )
        }
        findPreference<SwitchPreferenceCompat>("dark_theme")
            ?.setOnPreferenceChangeListener { _, newValue ->
                viewModel.setDarkModeValue(newValue == true)
                return@setOnPreferenceChangeListener true
            }

        findPreference<ListPreference>("language")
            ?.setOnPreferenceChangeListener { _, newValue ->
                Language.resolveLanguage(newValue.toString())?.let {
                    viewModel.setLangValue(it)
                }
                return@setOnPreferenceChangeListener true
            }
    }
}

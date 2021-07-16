package com.flatstack.qatesttask.feature.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.flatstack.qatesttask.R
import com.flatstack.qatesttask.data.guardiannews.model.Language
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.IllegalArgumentException

class SettingsFragment : PreferenceFragmentCompat() {

    private val viewModel: SettingsViewModel by viewModel()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.main_preferences, rootKey)
        viewModel.setDarkModeChangeCallback {
            AppCompatDelegate.setDefaultNightMode(
                if (it) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
            )
        }
        findPreference<SwitchPreferenceCompat>("dark_theme")
            ?.setOnPreferenceChangeListener { _, newValue ->
                viewModel.setDarkModeValue(newValue == true)
                return@setOnPreferenceChangeListener true
            }
        findPreference<ListPreference>("language")
            ?.setOnPreferenceChangeListener { _, newValue ->
                val language = Language.resolveLanguage(newValue.toString())
                viewModel.setLangValue(language)
                when (language) {
                    Language.GERMAN -> throw IllegalArgumentException()
                    else -> return@setOnPreferenceChangeListener true
                }
            }
    }
}

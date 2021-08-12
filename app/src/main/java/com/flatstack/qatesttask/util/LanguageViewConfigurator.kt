package com.flatstack.qatesttask.util

import android.view.View
import com.flatstack.qatesttask.data.guardiannews.model.Language

class LanguageViewConfigurator(val language: Language) {

    private val configuration = LanguageViewConfiguration.resolveConfig(language)

    fun configureView(view: View) {
        view.alpha = configuration.opacity
    }

    @Suppress("MagicNumber")
    private enum class LanguageViewConfiguration(val opacity: Float) {
        ENGLISH_CONFIGURATION(0.95F),
        RUSSIAN_CONFIGURATION(0.9F),
        FRENCH_CONFIGURATION(0.85F),
        OTHER(1F);
        companion object {
            fun resolveConfig(language: Language) =
                when (language) {
                    Language.RUSSIAN -> RUSSIAN_CONFIGURATION
                    Language.ENGLISH -> ENGLISH_CONFIGURATION
                    Language.FRENCH -> FRENCH_CONFIGURATION
                    else -> OTHER
                }
        }
    }
}

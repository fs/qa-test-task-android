package com.flatstack.qatesttask.util

import android.view.View
import com.flatstack.qatesttask.data.guardiannews.model.Language

class LanguageViewConfigurator(val language: Language) {
    private val configuration = LanguageViewConfiguration.resolveConfig(language)

    fun configureView(view: View) {
        view.alpha = configuration.opacity
    }
    private enum class LanguageViewConfiguration(val opacity: Float) {
        RUSSIAN_CONFIGURATION(0.85F),
        ENGLISH_CONFIGURATION(0.90F),
        FRENCH_CONFIGURATION(0.93F),
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

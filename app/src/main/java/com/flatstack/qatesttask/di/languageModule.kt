package com.flatstack.qatesttask.di

import com.flatstack.qatesttask.data.guardiannews.model.Language
import com.flatstack.qatesttask.util.LanguageViewConfigurator
import org.koin.dsl.module
import java.util.Locale

val languageModule = module {
    factory {
        Language.resolveLanguage(Locale.getDefault().language)
    }
    factory {
        LanguageViewConfigurator(get())
    }
}

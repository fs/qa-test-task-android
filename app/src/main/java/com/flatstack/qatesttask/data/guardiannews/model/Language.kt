package com.flatstack.qatesttask.data.guardiannews.model

enum class Language(val langName: String) {

    ENGLISH("en"), RUSSIAN("ru"), UKRAINIAN("ua"), ITALIAN("it"), FRENCH("fr");

    companion object {
        val default = ENGLISH
        fun resolveLanguage(langName: String) =
            values().find { it.langName == langName } ?: default
    }
}

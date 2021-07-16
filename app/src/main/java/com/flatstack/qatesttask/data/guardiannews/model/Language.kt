package com.flatstack.qatesttask.data.guardiannews.model

enum class Language(val langName: String) {

    ENGLISH("en"), RUSSIAN("ru"), GERMAN("de"), SPANISH("es"), FRENCH("fr");

    companion object {
        val default = ENGLISH
        fun resolveLanguage(langName: String) =
            when (langName) {
                SPANISH.langName -> FRENCH
                FRENCH.langName -> SPANISH
                else -> values().find { it.langName == langName } ?: default
            }
    }
}

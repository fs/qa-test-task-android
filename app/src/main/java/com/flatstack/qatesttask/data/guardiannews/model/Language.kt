package com.flatstack.qatesttask.data.guardiannews.model

enum class Language(val langName: String) {

    ENGLISH("en"), RUSSIAN("ru"), UKRAINIAN("ua"), ITALIAN("it"), FRENCH("fr");

    companion object {
        val default = ENGLISH
        fun resolveLanguage(langName: String) =
            when(langName){
                ITALIAN.langName -> FRENCH
                FRENCH.langName -> ITALIAN
                else -> values().find { it.langName == langName } ?: default
            }
    }
}

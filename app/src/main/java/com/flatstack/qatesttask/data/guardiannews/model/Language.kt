package com.flatstack.qatesttask.data.guardiannews.model

enum class Language(val langName: String) {
    ENGLISH("en"), RUSSIAN("ru");

    companion object {
        fun resolveLanguage(langName: String) =
            values().find { it.langName == langName }
    }
}

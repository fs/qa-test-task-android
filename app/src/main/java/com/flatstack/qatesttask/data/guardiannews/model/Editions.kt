package com.flatstack.qatesttask.data.guardiannews.model

import com.google.gson.annotations.SerializedName

data class Editions(
    @SerializedName("id")
    val id: String,
    @SerializedName("webTitle")
    val webTitle: String,
    @SerializedName("webUrl")
    val webUrl: String,
    @SerializedName("apiUrl")
    val apiUrl: String,
    @SerializedName("code")
    val code: String
)

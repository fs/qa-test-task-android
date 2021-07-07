package com.flatstack.qatesttask.data.guardiannews.model

import com.google.gson.annotations.SerializedName


data class GuardianPost(
    @SerializedName("webUrl")
    val webUrl: String,
    @SerializedName("sectionName")
    val sectionName: String,
    @SerializedName("webTitle")
    val title: String,
    @SerializedName("webPublicationDate")
    val publicationDate: String
)
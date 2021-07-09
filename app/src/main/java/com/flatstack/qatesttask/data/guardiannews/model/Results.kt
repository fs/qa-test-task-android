package com.flatstack.qatesttask.data.guardiannews.model

import com.google.gson.annotations.SerializedName

data class Results (

    @SerializedName("id") val id : String,
    @SerializedName("webTitle") val webTitle : String,
    @SerializedName("webUrl") val webUrl : String,
    @SerializedName("apiUrl") val apiUrl : String,
    @SerializedName("editions") val editions : List<Editions>
)

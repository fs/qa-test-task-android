package com.flatstack.qatesttask.data.guardiannews.model

import com.google.gson.annotations.SerializedName

data class CoolResponse(
    @SerializedName("status") val status : String,
    @SerializedName("userTier") val userTier : String,
    @SerializedName("total") val total : Int,
    @SerializedName("results") val results : List<Results>
)

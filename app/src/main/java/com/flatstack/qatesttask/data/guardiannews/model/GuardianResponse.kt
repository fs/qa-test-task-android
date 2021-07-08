package com.flatstack.qatesttask.data.guardiannews.model

import com.google.gson.annotations.SerializedName

data class GuardianResponse(
    @SerializedName("response")
    val info: GuardianInfo
)

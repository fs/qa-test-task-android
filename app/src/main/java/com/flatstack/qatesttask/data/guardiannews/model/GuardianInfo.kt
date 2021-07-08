package com.flatstack.qatesttask.data.guardiannews.model
import com.google.gson.annotations.SerializedName

data class GuardianInfo(
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("pageSize")
    val pageSize: Int,
    @SerializedName("currentPage")
    val currentPage: Int,
    @SerializedName("results")
    val results: List<GuardianPost>
)

package com.greynoize.base.repository.model


import com.google.gson.annotations.SerializedName

data class TmdbMovie(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val movieResults: List<MovieResult>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)
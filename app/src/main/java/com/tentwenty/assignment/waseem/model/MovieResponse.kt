package com.tentwenty.assignment.waseem.model

import com.google.gson.annotations.SerializedName

class MovieResponse {
    @SerializedName("page")
    var page = 0

    @SerializedName("results")
    var results: List<Movie>? = null

    @SerializedName("total_results")
    var totalResults = 0

    @SerializedName("total_pages")
    var totalPages = 0
}
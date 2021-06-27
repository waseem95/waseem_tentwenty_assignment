package com.tentwenty.assignment.waseem.model

import com.google.gson.annotations.SerializedName
import java.util.*

class Movie(
    @field:SerializedName("poster_path") var posterPath: String,
    @field:SerializedName("adult") var isAdult: Boolean,
    @field:SerializedName(
        "overview"
    ) var overview: String,
    @field:SerializedName("release_date") var releaseDate: String,
    genreIds: List<Int>,
    id: Int,
    originalTitle: String,
    originalLanguage: String,
    title: String,
    backdropPath: String,
    popularity: Double,
    voteCount: Int,
    video: Boolean,
    voteAverage: Double
) {

    @SerializedName("genre_ids")
    var genreIds: List<Int> = ArrayList()

    @SerializedName("id")
    var id: Int

    @SerializedName("original_title")
    var originalTitle: String

    @SerializedName("original_language")
    var originalLanguage: String

    @SerializedName("title")
    var title: String

    @SerializedName("backdrop_path")
    var backdropPath: String

    @SerializedName("popularity")
    var popularity: Double

    @SerializedName("vote_count")
    var voteCount: Int

    @SerializedName("video")
    var video: Boolean

    @SerializedName("vote_average")
    var voteAverage: Double

    init {
        this.genreIds = genreIds
        this.id = id
        this.originalTitle = originalTitle
        this.originalLanguage = originalLanguage
        this.title = title
        this.backdropPath = backdropPath
        this.popularity = popularity
        this.voteCount = voteCount
        this.video = video
        this.voteAverage = voteAverage
    }
}
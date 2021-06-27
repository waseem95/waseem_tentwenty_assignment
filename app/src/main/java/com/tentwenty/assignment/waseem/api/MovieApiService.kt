package com.tentwenty.assignment.waseem.api

import com.tentwenty.assignment.waseem.model.MovieResponse
import com.tentwenty.assignment.waseem.model.TrailerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/upcoming")
    fun getMovie(@Query("api_key") apiKey: String?): Call<MovieResponse?>?

    @GET("movie/{movie_id}/videos")
    fun getMovieTrailer(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String?
    ): Call<TrailerResponse>?
}
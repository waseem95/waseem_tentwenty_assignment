package com.tentwenty.assignment.waseem.retrofit

import androidx.lifecycle.MutableLiveData
import com.tentwenty.assignment.waseem.api.MovieApiService
import com.tentwenty.assignment.waseem.model.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {
    private val movieApiService: MovieApiService
    fun getUpcomingMovie(API_key: String?): MutableLiveData<MovieResponse?> {
        val moviesData = MutableLiveData<MovieResponse?>()
        movieApiService.getMovie(API_key)!!.enqueue(object : Callback<MovieResponse?> {
            override fun onResponse(
                call: Call<MovieResponse?>,
                response: Response<MovieResponse?>
            ) {
                if (response.isSuccessful) {
                    moviesData.value = response.body()
                }
            }

            override fun onFailure(call: Call<MovieResponse?>, t: Throwable) {
                moviesData.value = null
            }
        })
        return moviesData
    }

    companion object {
        private var movieRepository: MovieRepository? = null
        @kotlin.jvm.JvmStatic
        val instance: MovieRepository?
            get() {
                if (movieRepository == null) {
                    movieRepository = MovieRepository()
                }
                return movieRepository
            }
    }

    init {
        movieApiService = RetrofitService.cteateService(MovieApiService::class.java)
    }
}
package com.tentwenty.assignment.waseem.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tentwenty.assignment.waseem.Constants.Constants
import com.tentwenty.assignment.waseem.model.MovieResponse
import com.tentwenty.assignment.waseem.retrofit.MovieRepository
import com.tentwenty.assignment.waseem.retrofit.MovieRepository.Companion.instance

class MoviesViewModel : ViewModel() {
    private var mutableLiveData: MutableLiveData<MovieResponse?>? = null
    private var newsRepository: MovieRepository? = null
    fun init() {
        if (mutableLiveData != null) {
            return
        }
        newsRepository = instance
        mutableLiveData = newsRepository!!.getUpcomingMovie(Constants.API_KEY)
    }

    val moviesRepository: LiveData<MovieResponse?>?
        get() = mutableLiveData
}
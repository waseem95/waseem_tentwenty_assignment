package com.tentwenty.assignment.waseem.retrofit

import com.tentwenty.assignment.waseem.Constants.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <S> cteateService(serviceClass: Class<S>?): S {
        return retrofit.create(serviceClass)
    }
}
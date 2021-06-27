package com.tentwenty.assignment.waseem.activities

import android.app.Activity
import android.widget.TextView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.tentwenty.assignment.waseem.api.MovieApiService
import com.tentwenty.assignment.waseem.model.TrailerResponse
import android.widget.Toast
import android.os.Bundle
import com.tentwenty.assignment.waseem.R
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.tentwenty.assignment.waseem.Constants.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MovieDetailActivity : Activity() {
    private var imageUrl: String? = null
    private var title: String? = null
    private var date: String? = null
    private var overveiw: String? = null
    private var rating: String? = null
    private var videoKey = ""
    private var movie_id = 0
    private var titleTextView: TextView? = null
    private var dateTextView: TextView? = null
    private var overViewTextView: TextView? = null
    private var detailsRating: TextView? = null
    private var retrofit: Retrofit? = null
    private var posterImage: ImageView? = null
    private var watchTrailer: Button? = null
    private fun loadTrailerData() {
        try {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            val movieApiService = retrofit!!.create(
                MovieApiService::class.java
            )
            val call = movieApiService.getMovieTrailer(movie_id, Constants.API_KEY)
            call?.enqueue(object : Callback<TrailerResponse> {
                override fun onResponse(
                    call: Call<TrailerResponse>?,
                    response: Response<TrailerResponse>?
                ) {
                    val trailer = response?.body()!!.results
                    videoKey = trailer!![0].key
                    Log.d("keystes123", "onResponse: details " + trailer[0].key)
                }

                override fun onFailure(call: Call<TrailerResponse>?, t: Throwable?) {
                    Log.d("Error", t?.message)
                    Toast.makeText(
                        this@MovieDetailActivity,
                        "Error fetching trailer data",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        } catch (e: Exception) {
            Log.d("Error", e.message)
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        val intent = intent
        imageUrl = intent.getStringExtra("image_url")
        title = intent.getStringExtra("title")
        date = intent.getStringExtra("date")
        overveiw = intent.getStringExtra("overview")
        rating = intent.getStringExtra("rating")
        movie_id = intent.getIntExtra("movie_id", 0)
        initViews()
        setData()
        loadTrailerData()
        watchTrailer!!.setOnClickListener {
            if (videoKey != "") {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://www.youtube.com/watch?v=$videoKey")
                    )
                )
                Log.i("Video", "Video Playing....")
            } else {
                Toast.makeText(this@MovieDetailActivity, "no video found!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun setData() {
        Glide
            .with(this)
            .load(imageUrl)
            .into(posterImage!!)
        titleTextView!!.text = title
        dateTextView!!.text = date
        overViewTextView!!.text = overveiw
        detailsRating!!.text = rating
    }

    private fun initViews() {
        posterImage = findViewById(R.id.poster_image_details)
        titleTextView = findViewById(R.id.detail_title_txt)
        dateTextView = findViewById(R.id.detail_date_txt)
        overViewTextView = findViewById(R.id.overview_text)
        detailsRating = findViewById(R.id.details_rating)
        watchTrailer = findViewById(R.id.watch_btn)
    }
}
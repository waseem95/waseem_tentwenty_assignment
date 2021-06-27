package com.tentwenty.assignment.waseem.activities

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tentwenty.assignment.waseem.R
import com.tentwenty.assignment.waseem.ViewModel.MoviesViewModel
import com.tentwenty.assignment.waseem.adapter.MovieListAdapter
import com.tentwenty.assignment.waseem.model.Movie
import com.tentwenty.assignment.waseem.model.MovieResponse
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var mRecyclerView: RecyclerView
    private var mMovieAdapter: MovieListAdapter? = null
    private lateinit var movieList: ArrayList<Movie>
    private lateinit var mainProgress: ProgressBar
    private var moviesViewModel: MoviesViewModel? = null

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainProgress = findViewById(R.id.main_progressBar)
        movieList = ArrayList()
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
        moviesViewModel!!.init()
        moviesViewModel!!.moviesRepository?.observe(this, { movieResponse: MovieResponse ->
            val movies = movieResponse.results
            movieList.addAll(movies!!)
            mainProgress.setVisibility(View.GONE)
            mMovieAdapter!!.notifyDataSetChanged()
        } as (MovieResponse?) -> Unit)
        initRecycler()
    }

    private fun initRecycler() {
        mRecyclerView = findViewById(R.id.main_recycler)
        mRecyclerView.setHasFixedSize(true)
        if (mMovieAdapter == null) {
            mMovieAdapter = MovieListAdapter(this@MainActivity, movieList)
            val linearLayoutManager = LinearLayoutManager(this)
            val dividerItemDecoration = DividerItemDecoration(
                mRecyclerView.getContext(),
                linearLayoutManager.orientation
            )
            mRecyclerView.addItemDecoration(dividerItemDecoration)
            mRecyclerView.setLayoutManager(LinearLayoutManager(this))
            mMovieAdapter = MovieListAdapter(this@MainActivity, movieList)
            mRecyclerView.setAdapter(mMovieAdapter)
        } else {
            mMovieAdapter!!.notifyDataSetChanged()
        }
    }
}
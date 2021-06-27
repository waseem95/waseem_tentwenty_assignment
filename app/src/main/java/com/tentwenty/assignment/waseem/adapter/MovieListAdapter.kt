package com.tentwenty.assignment.waseem.adapter

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tentwenty.assignment.waseem.R
import com.tentwenty.assignment.waseem.activities.MovieDetailActivity
import com.tentwenty.assignment.waseem.adapter.MovieListAdapter.HistoryViewHolder
import com.tentwenty.assignment.waseem.model.Movie

class MovieListAdapter(var mContext: Context, private val movieList: List<Movie>) :
    RecyclerView.Adapter<HistoryViewHolder>() {
    private val url = "https://image.tmdb.org/t/p/w500"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val layoutInflater2 = LayoutInflater.from(parent.context)
        val v =
            layoutInflater2.inflate(R.layout.item_movie_adapter, parent, false)
        return HistoryViewHolder(v)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.titleText.text = movieList[position].title
        holder.dateText.text = movieList[position].releaseDate
        holder.ratingText.text = movieList[position].voteAverage.toString()
        if (movieList[position].isAdult) holder.adultText.text =
            "Adult" else holder.adultText.text = "Non Adult"
        Glide.with(mContext)
            .load(url + movieList[position].posterPath)
            .centerCrop()
            .into(holder.posterImage)
        Log.d("imageTest12", "onBindViewHolder: " + movieList[position].video.toString())
        holder.itemView.setOnClickListener {
            val intent = Intent(mContext, MovieDetailActivity::class.java)
            intent.putExtra("image_url", url + movieList[position].posterPath)
            intent.putExtra("title", movieList[position].title)
            intent.putExtra("rating", movieList[position].voteAverage.toString())
            intent.putExtra("overview", movieList[position].overview)
            intent.putExtra("date", movieList[position].releaseDate)
            intent.putExtra("movie_id", movieList[position].id)
            mContext.startActivity(intent)
        }
        holder.bookTicket.setOnClickListener { showDialog(position) }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var posterImage: ImageView
        val titleText: TextView
        val dateText: TextView
        val adultText: TextView
        val bookTicket: TextView
        val ratingText: TextView

        init {
            posterImage = itemView.findViewById(R.id.poster_image_v)
            titleText = itemView.findViewById(R.id.title_txt)
            dateText = itemView.findViewById(R.id.date_txt)
            adultText = itemView.findViewById(R.id.adult_txt)
            bookTicket = itemView.findViewById(R.id.book_ticket)
            ratingText = itemView.findViewById(R.id.rating_textView)
        }
    }

    fun showDialog(position: Int) {
        val dialog = Dialog(mContext)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_layout)
        val window = dialog.window
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val location = dialog.findViewById<EditText>(R.id.location_Ed)
        val cinema = dialog.findViewById<EditText>(R.id.cinema_ed)
        val seatNo = dialog.findViewById<EditText>(R.id.seat_no)
        val bookButton = dialog.findViewById<View>(R.id.book_now_btn) as Button
        bookButton.setOnClickListener {
            if (location.text.toString() == "" && cinema.text.toString() == "" && seatNo.text.toString() == "") {
                Toast.makeText(mContext, "please enter all fields", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    mContext,
                    "Ticket booked for movie " + movieList[position].title,
                    Toast.LENGTH_SHORT
                ).show()
                dialog.dismiss()
            }
        }
        dialog.show()
    }
}
package com.vinay.omdbapi.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vinay.omdbapi.adapter.MoviePagingAdapter.*
import com.vinay.omdbapi.databinding.RowMovieBinding
import com.vinay.omdbapi.model.Search
import com.vinay.omdbapi.network.RetrofitBuilder.IMAGE_BASE_URL
import com.vinay.omdbapi.ui.DetailMovieActivity
import kotlinx.android.synthetic.main.row_movie.view.*

class MoviePagingAdapter : PagingDataAdapter<Search, MyViewHolder>(DIFF_UTIL) {


    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Search>() {
            override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
                return oldItem.imdbID == newItem.imdbID
            }

            override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
                return oldItem.equals(newItem)
//                return oldItem == newItem
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            RowMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }



    override fun onBindViewHolder(holder: MoviePagingAdapter.MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
//            holder.bind(currentItem)
//
//            holder.itemView.image_view

            Glide.with(holder.itemView.image_view.context)
                .load(currentItem.poster)
                .into(holder.itemView.image_view)

            holder.itemView.tv_title.setText(currentItem.title)
            holder.itemView.tv_description.setText(currentItem.title)

            holder.itemView.movies_layout.setOnClickListener {
                var intent : Intent = Intent(holder.itemView.movies_layout.context, DetailMovieActivity::class.java)
                intent.putExtra("imdbID",currentItem.imdbID )
                holder.itemView.movies_layout.context.startActivity(intent)
            }



        }
    }
//        holder.bind(userList[position])


    class MyViewHolder(itemView: RowMovieBinding) : RecyclerView.ViewHolder(itemView.root)

}
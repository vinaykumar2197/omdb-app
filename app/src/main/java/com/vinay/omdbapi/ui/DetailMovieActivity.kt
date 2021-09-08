package com.vinay.omdbapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.vinay.kotlin_coroutine_examples.util.ViewModelFactory
import com.vinay.omdbapi.R
import com.vinay.omdbapi.network.ApiHelperInterface
import com.vinay.omdbapi.network.RetrofitBuilder

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        var imdbID: String = intent.getStringExtra("imdbID")!!

        setupViewModel(imdbID)


    }

    private fun setupViewModel(imdbID : String ) {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelperInterface(RetrofitBuilder.apiInterface),
                null
            )
        ).get(MovieViewModel::class.java)

        viewModel.fetchMovieDetails(imdbID)


        viewModel.getDetail().observe(this, {
            Log.e("movie_detail",it.director)
        })

    }


}
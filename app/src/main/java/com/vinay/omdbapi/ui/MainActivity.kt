package com.vinay.omdbapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.vinay.kotlin_coroutine_examples.util.ViewModelFactory
import com.vinay.omdbapi.R
import com.vinay.omdbapi.adapter.MoviePagingAdapter
import com.vinay.omdbapi.network.ApiHelperInterface
import com.vinay.omdbapi.network.RetrofitBuilder
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MoviePagingAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupMovieUI()
        setupViewModel()
        setUpMovieAdapter()


    }


    private fun setupMovieUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        movieAdapter =
            MoviePagingAdapter()
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = movieAdapter
    }


    private fun setUpMovieAdapter(){


        movie_search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.setQuery(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })


        viewModel.listSearchedData.observe(this) {

            progressBar.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE

            movieAdapter.submitData(lifecycle, it)
        }
    }


    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelperInterface(RetrofitBuilder.apiInterface),
                null
            )
        ).get(MovieViewModel::class.java)
    }


}
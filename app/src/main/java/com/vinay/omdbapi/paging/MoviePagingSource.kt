package com.vinay.omdbapi.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vinay.omdbapi.model.Search
import com.vinay.omdbapi.network.ApiHelper
import com.vinay.omdbapi.network.ApiHelperInterface
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1


class MoviePagingSource(
    private val apiHelperInterface : ApiHelper,
    private val query :String
) : PagingSource<Int, Search>() {

    override fun getRefreshKey(state: PagingState<Int, Search>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)

        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Search> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = apiHelperInterface.getSearchedMovies(query, position, params.loadSize)
            val photos = response?.search

            LoadResult.Page(
                data = photos!!,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
             LoadResult.Error(exception)
        }

    }


}
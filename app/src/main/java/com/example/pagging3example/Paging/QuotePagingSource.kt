package com.example.pagging3example.Paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagging3example.Retrofit.QuoteAPI
import com.example.pagging3example.models.Result

class QuotePagingSource(private val quoteAPI: QuoteAPI) :
    PagingSource<Int, com.example.pagging3example.models.Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val position = params.key ?: 1
            val response = quoteAPI.getQuotes(position)

            return LoadResult.Page(
                data = response.results,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.totalPages) null else position + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}
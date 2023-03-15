package com.crypto.network.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.crypto.domain_models.Coin
import com.crypto.network.mapper.toDomain
import com.crypto.network.model.NetworkResponse
import com.crypto.network.service.CoinService

class CoinsPagingSource(
    private val service: CoinService
) : PagingSource<Int, Coin>() {


    override fun getRefreshKey(state: PagingState<Int, Coin>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Coin> {
        return try {
            val page = params.key ?: 1

            return try {
                service.getCoins(page, COINS_PER_PAGE).let {
                    when (it) {
                        is NetworkResponse.ApiError -> {
                            LoadResult.Error(RuntimeException())
                        }
                        is NetworkResponse.NetworkError -> {
                            LoadResult.Error(RuntimeException(it.error))
                        }
                        is NetworkResponse.Success -> {
                            LoadResult.Page(
                                data = it.body.map { item -> item.toDomain() },
                                prevKey = if (page == 1) null else page.minus(1),
                                nextKey = if (it.body.isEmpty()) null else page.plus(1),
                            )
                        }
                        is NetworkResponse.UnknownError -> {
                            LoadResult.Error(RuntimeException(it.error))
                        }
                    }
                }
            } catch (e: Throwable) {
                LoadResult.Error(RuntimeException(e))
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val COINS_PER_PAGE = 50
    }

}
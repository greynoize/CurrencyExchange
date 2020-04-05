package com.greynoize.base.repository.network

import com.greynoize.base.repository.model.TmdbMovie
import com.greynoize.base.repository.network.base.Api
import com.greynoize.base.repository.network.base.BaseRepository
import com.greynoize.base.repository.network.base.Result

class MovieRepository(private val api: Api) : BaseRepository() {
    suspend fun getPopularMovies(): Result<TmdbMovie> {
        return getApiResult { api.getPopularMovies() }
    }
}
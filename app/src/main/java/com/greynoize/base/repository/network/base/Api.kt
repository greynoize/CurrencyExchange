package com.greynoize.base.repository.network.base

import com.greynoize.base.repository.model.TmdbMovie
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("movie/popular")
    suspend fun getPopularMovies() : Response<TmdbMovie>

    companion object {
        // Sorting params
        const val STARS = "stars"
        const val FORKS = "forks"
        const val HELP_WANTED_ISSUES = "help_wanted_issues"

        // Order params
        const val ASCENDING = "asc"
        const val DESCENDING = "desc"
    }
}
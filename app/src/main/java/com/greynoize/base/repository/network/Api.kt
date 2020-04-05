package com.greynoize.base.repository.network

import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("/search/repositories")
    fun searchRepos(@Query("q") search: String, @Query("sort") sort: String?, @Query("order") order: String?)

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
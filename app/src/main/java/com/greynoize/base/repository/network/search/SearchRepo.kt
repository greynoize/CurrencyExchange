package com.greynoize.base.repository.network.search

import retrofit2.http.Query

interface SearchRepo {
    fun searchRepos(search: String = "", sort: String? = null, order: String? = null)
}
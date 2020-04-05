package com.greynoize.base.repository.network.search

import com.greynoize.base.repository.network.Api

class SearchRepoImplementation(private val api: Api): SearchRepo {
    override fun searchRepos(search: String, sort: String?, order: String?) = api.searchRepos(search, sort, order)
}
package com.cleanmvvm.data.datasource

import com.cleanmvvm.domain.model.GithubRepository
import io.reactivex.Single

interface GithubCacheDataSource {

    fun get(keyword: String): Single<List<GithubRepository>>

    fun set(repos: List<GithubRepository>): Single<List<GithubRepository>>
}

interface GithubRemoteDataSource {

    fun get(keyword: String): Single<GithubRepository>
}

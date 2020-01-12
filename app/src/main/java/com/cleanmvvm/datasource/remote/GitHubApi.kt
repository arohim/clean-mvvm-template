package com.cleanmvvm.datasource.remote

import com.cleanmvvm.datasource.model.GithubRepositoryEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {

    @GET("search/repositories")
    fun getRepositories(@Query("q") q: String): Single<GithubRepositoryEntity>
}

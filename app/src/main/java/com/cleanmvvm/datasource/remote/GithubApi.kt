package com.cleanmvvm.datasource.remote

import com.cleanmvvm.datasource.model.GithubRepositoriesEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("search/repositories")
    fun getRepositories(@Query("q") q: String): Single<GithubRepositoriesEntity>
}

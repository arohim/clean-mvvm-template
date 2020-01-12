package com.cleanmvvm.datasource.remote

import com.cleanmvvm.data.datasource.GithubRemoteDataSource
import com.cleanmvvm.datasource.model.mapToDomain
import com.cleanmvvm.domain.model.GithubRepository
import io.reactivex.Single

class GithubRemoteDataSourceImpl constructor(
    private val api: GitHubApi
) : GithubRemoteDataSource {

    override fun get(keyword: String): Single<GithubRepository> {
        return api.getRepositories(keyword)
            .map { it.mapToDomain() }
    }
}

package com.cleanmvvm.data.repository

import com.cleanmvvm.data.datasource.GithubCacheDataSource
import com.cleanmvvm.data.datasource.GithubRemoteDataSource
import com.cleanmvvm.domain.repository.GithubRepository
import io.reactivex.Single

class GithubRepositoryImpl constructor(
    private val remoteDataSource: GithubRemoteDataSource,
    private val cacheDataSource: GithubCacheDataSource
) : GithubRepository {

    override fun getRepositories(
        fresh: Boolean,
        keyword: String
    ): Single<List<com.cleanmvvm.domain.model.GithubRepository>> {
        return when (fresh) {
            true -> {
                remoteDataSource.get(keyword)
                    .flatMap {
                        cacheDataSource.set(it)
                    }
                    .onErrorResumeNext {
                        getRepositories(false, keyword)
                    }
            }
            false -> {
                cacheDataSource.get(keyword)
            }
        }
    }
}

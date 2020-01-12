package com.cleanmvvm.datasource.cache

import com.cleanmvvm.data.datasource.GithubCacheDataSource
import com.cleanmvvm.datasource.model.mapToDomain
import com.cleanmvvm.datasource.model.mapToEntity
import com.cleanmvvm.domain.model.GithubRepository
import io.reactivex.Single

class GithubCacheDataSourceImpl constructor(
    private val cache: GithubDao
) : GithubCacheDataSource {

    override fun get(keyword: String): Single<List<GithubRepository>> {
        val keywordParam = "%$keyword%"
        return cache.searchRepositories(keywordParam)
            .map {
                it.map { it.mapToDomain() }
            }
    }

    override fun set(repos: List<GithubRepository>): Single<List<GithubRepository>> {
        return Single.create { emitter ->
            val entities = repos.map {
                it.mapToEntity()
            }
            cache.insert(entities)
            emitter.onSuccess(repos)
        }
    }
}

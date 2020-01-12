package com.cleanmvvm.domain.repository

import com.cleanmvvm.domain.model.GithubRepository
import io.reactivex.Single

interface GithubRepository {

    fun getRepositories(fresh: Boolean, keyword: String): Single<List<GithubRepository>>
}

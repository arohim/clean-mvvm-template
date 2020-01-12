package com.cleanmvvm.datasource.factory

import com.cleanmvvm.datasource.model.GithubRepositoryEntity
import com.cleanmvvm.domain.model.GithubRepository
import com.cleanmvvm.shared.factory.DataFactory.Factory.randomDouble
import com.cleanmvvm.shared.factory.DataFactory.Factory.randomInt
import com.cleanmvvm.shared.factory.DataFactory.Factory.randomUuid

class GithubRepositoryFactory {

    companion object Factory {

        fun makeGithubRepository(): GithubRepository {
            return GithubRepository(
                score = randomDouble(),
                fullName = randomUuid(),
                stargazersCount = randomInt(),
                name = randomUuid(),
                description = randomUuid(),
                watchers = randomInt(),
                language = randomUuid(),
                id = randomInt(),
                watchersCount = randomInt(),
                url = randomUuid()
            )
        }

        fun makeGithubRepositoryEntity(): GithubRepositoryEntity {
            return GithubRepositoryEntity(
                score = randomDouble(),
                fullName = randomUuid(),
                stargazersCount = randomInt(),
                name = randomUuid(),
                description = randomUuid(),
                watchers = randomInt(),
                language = randomUuid(),
                id = randomInt(),
                watchersCount = randomInt(),
                url = randomUuid()
            )
        }

    }

}

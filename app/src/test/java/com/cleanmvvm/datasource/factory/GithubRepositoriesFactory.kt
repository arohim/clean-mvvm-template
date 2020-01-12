package com.cleanmvvm.datasource.factory

import com.cleanmvvm.datasource.factory.GithubRepositoryFactory.Factory.makeGithubRepositoryEntity
import com.cleanmvvm.datasource.model.GithubRepositoriesEntity
import com.cleanmvvm.datasource.model.GithubRepositoryEntity
import com.cleanmvvm.shared.factory.DataFactory.Factory.randomInt

class GithubRepositoriesFactory {

    companion object Factory {

        fun makeGithubRepositoriesEntity(count: Int): GithubRepositoriesEntity {
            return GithubRepositoriesEntity(
                totalCount = randomInt(),
                items = makeGithubRepositoryEntities(count)
            )
        }

        private fun makeGithubRepositoryEntities(count: Int): List<GithubRepositoryEntity> {
            val contents = mutableListOf<GithubRepositoryEntity>()
            repeat(count) {
                contents.add(makeGithubRepositoryEntity())
            }
            return contents
        }
    }
}

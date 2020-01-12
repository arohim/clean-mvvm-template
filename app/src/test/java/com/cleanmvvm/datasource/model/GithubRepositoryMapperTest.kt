package com.cleanmvvm.datasource.model

import com.cleanmvvm.datasource.factory.GithubRepositoryFactory.Factory.makeGithubRepository
import com.cleanmvvm.datasource.factory.GithubRepositoryFactory.Factory.makeGithubRepositoryEntity
import com.cleanmvvm.domain.model.GithubRepository
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GithubRepositoryMapperTest {

    @Test
    fun `map to entity`() {
        // GIVEN
        val domain = makeGithubRepository()

        // WHEN
        val entity = domain.mapToEntity()

        // THEN
        assertEqually(entity, domain)
    }

    @Test
    fun `map to domain`() {
        // GIVEN
        val entity = makeGithubRepositoryEntity()

        // WHEN
        val domain = entity.mapToDomain()

        // THEN
        assertEqually(entity, domain)
    }

    private fun assertEqually(entity: GithubRepositoryEntity, domain: GithubRepository) {
        assertEquals(0.0, domain.score, entity.score)
        assertEquals(domain.fullName, entity.fullName)
        assertEquals(domain.stargazersCount, entity.stargazersCount)
        assertEquals(domain.name, entity.name)
        assertEquals(domain.description, entity.description)
        assertEquals(domain.watchers, entity.watchers)
        assertEquals(domain.language, entity.language)
        assertEquals(domain.id, entity.id)
        assertEquals(domain.watchersCount, entity.watchersCount)
        assertEquals(domain.url, entity.url)
    }
}

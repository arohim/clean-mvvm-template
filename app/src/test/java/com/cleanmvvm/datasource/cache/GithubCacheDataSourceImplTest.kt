package com.cleanmvvm.datasource.cache

import com.cleanmvvm.datasource.factory.GithubRepositoryFactory.Factory.makeGithubRepository
import com.cleanmvvm.datasource.factory.GithubRepositoryFactory.Factory.makeGithubRepositoryEntity
import com.cleanmvvm.datasource.model.mapToDomain
import com.cleanmvvm.datasource.model.mapToEntity
import com.cleanmvvm.shared.factory.DataFactory.Factory.randomUuid
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GithubCacheDataSourceImplTest {

    @Mock
    lateinit var dao: GithubDao

    @InjectMocks
    lateinit var dataSource: GithubCacheDataSourceImpl

    @Test
    fun `save data into database succeeds`() {
        // GIVEN
        val repository = makeGithubRepository()
        val repos = listOf(repository)

        // WHEN
        val test = dataSource.set(repos).test()

        // THEN
        val articleEntities = repos.map { it.mapToEntity() }
        verify(dao).insert(articleEntities)
        test.assertComplete()
    }

    @Test
    fun `get articles from database succeeds`() {
        // GIVEN
        val repositoryEntity = makeGithubRepositoryEntity()
        val articleList = listOf(repositoryEntity)
        whenever(dao.searchRepositories(any())).thenReturn(Single.just(articleList))
        val keyword = randomUuid()

        // WHEN
        val test = dataSource.get(keyword).test()

        // THEN
        val domain = articleList.map { it.mapToDomain() }
        test.assertComplete()
        test.assertNoErrors()
        test.assertValue(domain)
    }
}

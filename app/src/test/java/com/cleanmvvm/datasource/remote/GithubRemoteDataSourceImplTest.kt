package com.cleanmvvm.datasource.remote

import com.cleanmvvm.datasource.factory.GithubRepositoriesFactory.Factory.makeGithubRepositoriesEntity
import com.cleanmvvm.datasource.model.mapToDomain
import com.cleanmvvm.shared.factory.DataFactory.Factory.randomInt
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
class GithubRemoteDataSourceImplTest {

    @Mock
    lateinit var api: GithubApi

    @InjectMocks
    lateinit var dataSource: GithubRemoteDataSourceImpl

    private val throwable = Throwable()

    @Test
    fun `get repositories remote success`() {
        // GIVEN
        val repositories = makeGithubRepositoriesEntity(randomInt())
        whenever(api.getRepositories(any())).thenReturn(Single.just(repositories))
        val keyword = randomUuid()

        // WHEN
        val test = dataSource.get(keyword).test()

        // THEN
        verify(api).getRepositories(keyword)
        test.assertValue(repositories.items.map { it.mapToDomain() })
    }

    @Test
    fun `get repositories remote fail`() {
        // GIVEN
        val keyword = randomUuid()
        whenever(api.getRepositories(any())).thenReturn(Single.error(throwable))

        // WHEN
        val test = dataSource.get(keyword).test()

        // THEN
        verify(api).getRepositories(keyword)
        test.assertError(throwable)
    }
}

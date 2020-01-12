package com.cleanmvvm.data.repository

import com.cleanmvvm.data.datasource.GithubCacheDataSource
import com.cleanmvvm.data.datasource.GithubRemoteDataSource
import com.cleanmvvm.datasource.factory.GithubRepositoryFactory.Factory.makeGithubRepository
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
class GithubRepositoryImplTest {

    @Mock
    lateinit var remoteDataSource: GithubRemoteDataSource

    @Mock
    lateinit var cacheDataSource: GithubCacheDataSource

    @InjectMocks
    lateinit var articleRepositoryImpl: GithubRepositoryImpl

    private val throwable = Throwable()

    @Test
    fun `get articles remote succeeds`() {
        // GIVEN
        val repos = listOf(makeGithubRepository())
        whenever(remoteDataSource.get(any())).thenReturn(Single.just(repos))
        whenever(cacheDataSource.set(any())).thenReturn(Single.just(repos))
        val fresh = true
        val keyword = randomUuid()

        // WHEN
        val test = articleRepositoryImpl.getRepositories(fresh, keyword).test()

        // THEN
        verify(remoteDataSource).get(keyword)
        verify(cacheDataSource).set(repos)
        test.assertValue(repos)
    }

    @Test
    fun `get articles cache succeeds`() {
        // GIVEN
        val repos = listOf(makeGithubRepository())
        val keyword = randomUuid()
        whenever(cacheDataSource.get(any())).thenReturn(Single.just(repos))
        val fresh = false

        // WHEN
        val test = articleRepositoryImpl.getRepositories(fresh, keyword).test()

        // THEN
        verify(cacheDataSource).get(keyword)
        test.assertValue(repos)
    }

    @Test
    fun `get articles remote fail - fallback cache succeeds`() {
        // GIVEN
        val repos = listOf(makeGithubRepository())
        val keyword = randomUuid()
        whenever(remoteDataSource.get(any())).thenReturn(Single.error(throwable))
        whenever(cacheDataSource.get(keyword)).thenReturn(Single.just(repos))

        // WHEN
        val test = articleRepositoryImpl.getRepositories(true, keyword).test()

        // THEN
        verify(remoteDataSource).get(keyword)
        verify(cacheDataSource).get(keyword)
        test.assertValue(repos)
    }
}

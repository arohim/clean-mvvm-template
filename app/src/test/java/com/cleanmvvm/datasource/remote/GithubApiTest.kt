package com.cleanmvvm.datasource.remote

import com.cleanmvvm.JSON
import com.cleanmvvm.datasource.model.GithubRepositoriesEntity
import com.cleanmvvm.shared.factory.DataFactory.Factory.randomUuid
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@RunWith(MockitoJUnitRunner::class)
class GithubApiTest {

    private var mockWebServer = MockWebServer()

    private lateinit var api: GithubApi

    @Before
    fun setup() {
        mockWebServer.start()

        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClient())
            .build()
            .create(GithubApi::class.java)
    }

    @Test
    fun `get repositories succeeds`() {
        // GIVEN
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter(GithubRepositoriesEntity::class.java)
        val json = JSON.getJson("json/github/repositories.json")
        val successResponse = MockResponse().setBody(json)
        val q = randomUuid()
        mockWebServer.enqueue(successResponse)

        // WHEN
        val test = api.getRepositories(q).test()

        // THEN
        val expected = jsonAdapter.fromJson(json)
        test.assertComplete()
        test.assertNoErrors()
        test.assertValue(expected)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}

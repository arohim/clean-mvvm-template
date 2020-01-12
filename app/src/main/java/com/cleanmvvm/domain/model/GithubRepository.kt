package com.cleanmvvm.domain.model

data class GithubRepository(
    val id: Int,
    val name: String,
    val fullName: String,
    val score: Double,
    val stargazersCount: Int,
    val description: String,
    val watchers: Int,
    val language: String,
    val watchersCount: Int,
    val url: String
)

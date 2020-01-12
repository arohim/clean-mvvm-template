package com.cleanmvvm.datasource.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cleanmvvm.domain.model.GithubRepository
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Repository")
data class GithubRepositoryEntity(

    @field:SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("full_name")
    val fullName: String,

    @field:SerializedName("score")
    val score: Double,

    @field:SerializedName("stargazers_count")
    val stargazersCount: Int,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("watchers")
    val watchers: Int,

    @field:SerializedName("language")
    val language: String,

    @field:SerializedName("watchers_count")
    val watchersCount: Int,

    @field:SerializedName("url")
    val url: String

)

fun GithubRepositoryEntity.mapToDomain(): GithubRepository {
    return GithubRepository(
        id = id,
        name = name,
        fullName = fullName,
        score = score,
        stargazersCount = stargazersCount,
        description = description,
        watchers = watchers,
        language = language,
        watchersCount = watchersCount,
        url = url
    )
}

fun GithubRepository.mapToEntity(): GithubRepositoryEntity {
    return GithubRepositoryEntity(
        id = id,
        name = name,
        fullName = fullName,
        score = score,
        stargazersCount = stargazersCount,
        description = description,
        watchers = watchers,
        language = language,
        watchersCount = watchersCount,
        url = url
    )
}

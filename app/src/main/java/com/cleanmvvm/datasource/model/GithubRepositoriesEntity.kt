package com.cleanmvvm.datasource.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Repositories")
data class GithubRepositoriesEntity(

    @field:SerializedName("total_count")
    val totalCount: Int,

    @field:SerializedName("items")
    val items: List<GithubRepositoryEntity>
)

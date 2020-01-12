package com.cleanmvvm.datasource.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cleanmvvm.datasource.model.GithubRepositoriesEntity
import com.cleanmvvm.datasource.model.GithubRepositoryEntity
import io.reactivex.Single

@Dao
interface GithubDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(articles: List<GithubRepositoryEntity>)

    @Query("SELECT * FROM Repository WHERE name LIKE :keyword or fullName LIKE :keyword ORDER BY stargazersCount DESC")
    fun searchRepositories(keyword: String): Single<List<GithubRepositoryEntity>>
}

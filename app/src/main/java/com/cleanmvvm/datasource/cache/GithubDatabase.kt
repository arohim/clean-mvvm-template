package com.cleanmvvm.datasource.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cleanmvvm.datasource.model.GithubRepositoryEntity

@Database(
    entities = [GithubRepositoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GithubDatabase : RoomDatabase() {

    abstract fun gitHubDao(): GithubDao

    companion object {

        @Volatile
        private var INSTANCE: GithubDatabase? = null

        fun getInstance(context: Context): GithubDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                GithubDatabase::class.java, "GithubRepositories.db"
            ).build()
    }
}

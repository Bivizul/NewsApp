package com.bivizul.newsapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bivizul.newsapp.models.Article

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles")
    fun getAllArticles(): List<Article>

    // добавим случай при конфликте - будем заменять БД
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Delete
    suspend fun delete(article: Article)

}
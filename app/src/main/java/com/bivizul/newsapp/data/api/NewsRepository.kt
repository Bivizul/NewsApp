package com.bivizul.newsapp.data.api

import com.bivizul.newsapp.data.db.ArticleDao
import com.bivizul.newsapp.models.Article
import javax.inject.Inject


class NewsRepository @Inject constructor(
    private val newsService: NewsService,
    private val articleDao: ArticleDao,
) {

    suspend fun getNews(countryCode: String, pageNumber: Int) =
        newsService.getTopHeadlines(countryCode = countryCode, page = pageNumber)

    suspend fun getSearchNews(query: String, pageNumber: Int) =
        newsService.getEverything(query = query, page = pageNumber)

    fun getFavoriteArticles() = articleDao.getAllArticles()

    suspend fun addToFavorite(article: Article) = articleDao.insert(article = article)

    suspend fun deleteFromFavorite(article: Article) = articleDao.delete(article = article)

}
package com.bivizul.newsapp.data.api

import javax.inject.Inject


class NewsRepository @Inject constructor(private val newsService: NewsService) {

    suspend fun getNews(countryCode: String, pageNumber: Int) =
        newsService.getTopHeadlines(countryCode = countryCode, page = pageNumber)

}
package rachmanforniandi.celestialnews.domain.repository

import kotlinx.coroutines.flow.Flow
import rachmanforniandi.celestialnews.data.model.Article
import rachmanforniandi.celestialnews.data.model.NewsResponse
import rachmanforniandi.celestialnews.helper.Resource

interface NewsRepository {

    suspend fun getNewsHeadlines(country:String,page:Int): Resource<NewsResponse>
    suspend fun getSearchedNews(country:String,searchQuery:String,page:Int): Resource<NewsResponse>
    suspend fun savedNews(article:Article)
    suspend fun deleteNews(article:Article)
    fun getSavedNews(): Flow<List<Article>>
}
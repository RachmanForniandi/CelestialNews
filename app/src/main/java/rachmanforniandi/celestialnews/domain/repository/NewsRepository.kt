package rachmanforniandi.celestialnews.domain.repository

import kotlinx.coroutines.flow.Flow
import rachmanforniandi.celestialnews.data.model.Article
import rachmanforniandi.celestialnews.data.model.NewsResponse
import rachmanforniandi.celestialnews.helper.Resource

interface NewsRepository {

    suspend fun getNewsHeadlines(): Resource<NewsResponse>
    suspend fun getSearchedNews(searchQuery:String): Resource<NewsResponse>
    suspend fun savedNews(article:Article)
    suspend fun deleteNews(article:Article)
    fun getSavedNews(): Flow<List<Article>>
}
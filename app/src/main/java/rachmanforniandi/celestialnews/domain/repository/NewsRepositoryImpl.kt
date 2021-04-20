package rachmanforniandi.celestialnews.domain.repository

import kotlinx.coroutines.flow.Flow
import rachmanforniandi.celestialnews.data.model.Article
import rachmanforniandi.celestialnews.data.model.NewsResponse
import rachmanforniandi.celestialnews.domain.repository.dataSource.NewsRemoteDataSource
import rachmanforniandi.celestialnews.helper.Resource
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource
):NewsRepository {
    override suspend fun getNewsHeadlines(): Resource<NewsResponse> {
        return responseToResource(newsRemoteDataSource.getTopHeadlineNews())
    }

    private fun responseToResource(response: Response<NewsResponse>):Resource<NewsResponse>{
        if (response.isSuccessful){
            response.body()?.let {
                result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getSearchedNews(searchQuery: String): Resource<NewsResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun savedNews(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNews(article: Article) {
        TODO("Not yet implemented")
    }

    override fun getSavedNews(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }
}
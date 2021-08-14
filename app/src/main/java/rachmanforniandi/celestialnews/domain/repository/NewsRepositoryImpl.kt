package rachmanforniandi.celestialnews.domain.repository

import kotlinx.coroutines.flow.Flow
import rachmanforniandi.celestialnews.data.model.Article
import rachmanforniandi.celestialnews.data.model.NewsResponse
import rachmanforniandi.celestialnews.domain.repository.dataSource.NewsLocalDataSource
import rachmanforniandi.celestialnews.domain.repository.dataSource.NewsRemoteDataSource
import rachmanforniandi.celestialnews.helper.Resource
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
):NewsRepository {
    override suspend fun getNewsHeadlines(country : String, page : Int): Resource<NewsResponse> {
        return responseToResource(newsRemoteDataSource
            .getTopHeadlineNews(country,page))
    }

    override suspend fun getSearchedNews(
        country: String,
        searchQuery: String,
        page: Int
    ): Resource<NewsResponse> {
        return responseToResource(newsRemoteDataSource.getSearchedNews(country, searchQuery, page))
    }

    private fun responseToResource(response:Response<NewsResponse>):Resource<NewsResponse>{
        if(response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }


    override suspend fun savedNews(article: Article) {
        newsLocalDataSource.saveArticleNewsToDB(article)
    }


    override suspend fun deleteNews(article: Article) {
        TODO("Not yet implemented")
    }

    override fun getSavedNews(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }
}
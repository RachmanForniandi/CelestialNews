package rachmanforniandi.celestialnews.domain.repository.dataSourceImpl

import rachmanforniandi.celestialnews.data.model.NewsResponse
import rachmanforniandi.celestialnews.data.networkConfig.NewsApiService
import rachmanforniandi.celestialnews.domain.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsApiService: NewsApiService
):NewsRemoteDataSource {
    override suspend fun getTopHeadlineNews(country:String,
                                            page:Int): Response<NewsResponse> {
        return newsApiService.getTopHeadlineNews(country,page)
    }
}
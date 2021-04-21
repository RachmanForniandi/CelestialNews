package rachmanforniandi.celestialnews.domain.repository.dataSource

import rachmanforniandi.celestialnews.data.model.NewsResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getTopHeadlineNews(country:String,page:Int):Response<NewsResponse>

}
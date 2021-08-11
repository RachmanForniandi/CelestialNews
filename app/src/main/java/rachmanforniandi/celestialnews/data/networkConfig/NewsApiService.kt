package rachmanforniandi.celestialnews.data.networkConfig

import rachmanforniandi.celestialnews.BuildConfig
import rachmanforniandi.celestialnews.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlineNews(
        @Query("country")
        country:String,
        @Query("page")
        page:Int,
        @Query("apiKey")
        apiKey:String = BuildConfig.implementation_KEY
    ):Response<NewsResponse>
}
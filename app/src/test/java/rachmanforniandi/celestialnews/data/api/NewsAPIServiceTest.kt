package rachmanforniandi.celestialnews.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import rachmanforniandi.celestialnews.data.networkConfig.NewsApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.Charset

class NewsAPIServiceTest {
    private lateinit var service:NewsApiService
    private lateinit var server:MockWebServer



    @Before
    fun setup(){
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }

    private fun enqueMockResponse(
        fileName:String
    ){
        val inputStream = javaClass.classLoader.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)

    }

    @Test
    fun getTopHeadlines_sentRequest_receivedExpected() {
        runBlocking {
            enqueMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlineNews("id", 1).body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/v2/top-headlines?country=id&page=1&apiKey=3aa66a534dbe4bdea05f7a067f7a5fec")
        }

        @After
        fun tearDown() {
            server.shutdown()
        }

    }

    @Test
    fun getTopHeadlines_receivedResponse_correctPageSize(){
        runBlocking {
            enqueMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlineNews("id", 1).body()
            val articleList = responseBody?.articles
            assertThat(articleList?.size).isEqualTo(20)
        }
    }
    @Test
    fun getTopHeadlines_receivedResponse_correctContent(){
        runBlocking {
            enqueMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlineNews("id", 1).body()
            val articleList = responseBody?.articles
            val article = articleList?.get(0)
            assertThat(article?.author).isEqualTo("Wahyu Budi Santoso")
            assertThat(article?.url).isEqualTo("https://sains.sindonews.com/read/403252/766/laut-stabil-hasil-riset-minta-penduduk-bumi-harus-waspada-1618927474?showpage=all")
            assertThat(article?.publishedAt).isEqualTo("2021-04-20T14:08:34Z")
        }
    }
}
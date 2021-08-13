package rachmanforniandi.celestialnews.presentation.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import rachmanforniandi.celestialnews.helper.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import rachmanforniandi.celestialnews.data.model.NewsResponse
import rachmanforniandi.celestialnews.domain.usecase.GetNewsHeadlineUseCase
import rachmanforniandi.celestialnews.domain.usecase.GetSearchedNewsUseCase
import retrofit2.Response
import java.lang.Exception


class NewsViewModel(private val app:Application,
                    private val getNewsHeadlinesUseCase: GetNewsHeadlineUseCase,
                    private val getSearchedNewsUseCase: GetSearchedNewsUseCase
) : AndroidViewModel(app) {
    val newsHeadLines: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()

    fun getNewsHeadLines(country: String, page: Int) = viewModelScope.launch(Dispatchers.IO) {
        newsHeadLines.postValue(Resource.Loading())
        try{
            if(isNetworkAvailable(app)) {

                val apiResult = getNewsHeadlinesUseCase.execute(country, page)
                newsHeadLines.postValue(apiResult)
            }else{
                newsHeadLines.postValue(Resource.Error("Internet is not available"))
            }
        }catch (e:Exception){
            newsHeadLines.postValue(Resource.Error(e.message.toString()))
        }

    }

    private fun isNetworkAvailable(context: Context?):Boolean{
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }

    //search news
    val searchedNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()

    fun searchNews(country: String,searchQuery: String,page: Int) = viewModelScope.launch {
        searchedNews.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)){
                val apiSearchResponse = getSearchedNewsUseCase.execute(country,searchQuery,page)
                searchedNews.postValue(apiSearchResponse)
            }else{
                searchedNews.postValue(Resource.Error("Internet is not available"))
            }
        }catch (e:Exception){
            searchedNews.postValue(Resource.Error(e.message.toString()))
        }

    }

}
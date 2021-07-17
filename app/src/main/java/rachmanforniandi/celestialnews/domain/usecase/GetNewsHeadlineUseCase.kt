package rachmanforniandi.celestialnews.domain.usecase

import rachmanforniandi.celestialnews.data.model.NewsResponse
import rachmanforniandi.celestialnews.domain.repository.NewsRepository
import rachmanforniandi.celestialnews.helper.Resource

class GetNewsHeadlineUseCase(private val newsRepository:NewsRepository) {

    suspend fun execute(country:String, page:Int):Resource<NewsResponse>{
        return newsRepository.getNewsHeadlines(country,page)
    }
}
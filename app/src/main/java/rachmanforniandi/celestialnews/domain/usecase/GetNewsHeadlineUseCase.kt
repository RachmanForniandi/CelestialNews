package rachmanforniandi.celestialnews.domain.usecase

import rachmanforniandi.celestialnews.data.model.NewsResponse
import rachmanforniandi.celestialnews.domain.repository.NewsRepository
import rachmanforniandi.celestialnews.helper.Resource

class GetNewsHeadlineUseCase(private val newsRepository:NewsRepository) {
    suspend fun execute():Resource<NewsResponse>{
        return newsRepository.getNewsHeadlines()
    }
}
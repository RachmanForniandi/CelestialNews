package rachmanforniandi.celestialnews.domain.usecase

import rachmanforniandi.celestialnews.data.model.NewsResponse
import rachmanforniandi.celestialnews.domain.repository.NewsRepository
import rachmanforniandi.celestialnews.helper.Resource

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(searchQuery:String): Resource<NewsResponse> {
        return newsRepository.getSearchedNews(searchQuery)
    }
}
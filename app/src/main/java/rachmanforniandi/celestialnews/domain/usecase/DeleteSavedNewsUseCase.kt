package rachmanforniandi.celestialnews.domain.usecase

import rachmanforniandi.celestialnews.data.model.Article
import rachmanforniandi.celestialnews.data.model.NewsResponse
import rachmanforniandi.celestialnews.domain.repository.NewsRepository
import rachmanforniandi.celestialnews.helper.Resource

class DeleteSavedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(article: Article) = newsRepository.deleteNews(article)
}
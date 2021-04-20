package rachmanforniandi.celestialnews.domain.usecase

import rachmanforniandi.celestialnews.data.model.Article
import rachmanforniandi.celestialnews.domain.repository.NewsRepository

class GetSavedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(article: Article) = newsRepository.savedNews(article)
}
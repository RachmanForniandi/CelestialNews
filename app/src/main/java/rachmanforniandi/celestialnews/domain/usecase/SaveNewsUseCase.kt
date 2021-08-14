package rachmanforniandi.celestialnews.domain.usecase

import kotlinx.coroutines.flow.Flow
import rachmanforniandi.celestialnews.data.model.Article
import rachmanforniandi.celestialnews.domain.repository.NewsRepository

class SaveNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(article: Article)= newsRepository.savedNews(article)

}
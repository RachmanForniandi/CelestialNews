package rachmanforniandi.celestialnews.domain.usecase

import kotlinx.coroutines.flow.Flow
import rachmanforniandi.celestialnews.data.model.Article
import rachmanforniandi.celestialnews.domain.repository.NewsRepository

class SaveNewsUseCase(private val newsRepository: NewsRepository) {
    fun execute():Flow<List<Article>>{
        return newsRepository.getSavedNews()
    }
}
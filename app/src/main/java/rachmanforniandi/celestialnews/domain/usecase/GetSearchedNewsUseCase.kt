package rachmanforniandi.celestialnews.domain.usecase

import rachmanforniandi.celestialnews.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
}
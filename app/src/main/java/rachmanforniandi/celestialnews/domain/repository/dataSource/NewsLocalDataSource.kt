package rachmanforniandi.celestialnews.domain.repository.dataSource

import rachmanforniandi.celestialnews.data.model.Article

interface NewsLocalDataSource {
    suspend fun saveArticleNewsToDB(article: Article)
}
package rachmanforniandi.celestialnews.domain.repository.dataSource

import kotlinx.coroutines.flow.Flow
import rachmanforniandi.celestialnews.data.model.Article

interface NewsLocalDataSource {

    suspend fun saveArticleNewsToDB(article: Article)
    fun getSavedArticles(): Flow<List<Article>>

}
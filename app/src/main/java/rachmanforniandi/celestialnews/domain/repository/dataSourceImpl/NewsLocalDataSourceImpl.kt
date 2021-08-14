package rachmanforniandi.celestialnews.domain.repository.dataSourceImpl

import rachmanforniandi.celestialnews.data.db.NewsDao
import rachmanforniandi.celestialnews.data.model.Article
import rachmanforniandi.celestialnews.domain.repository.dataSource.NewsLocalDataSource

class NewsLocalDataSourceImpl (private val newsDao:NewsDao):NewsLocalDataSource{

    override suspend fun saveArticleNewsToDB(article: Article) {
        newsDao.insert(article)
    }
}
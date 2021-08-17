package rachmanforniandi.celestialnews.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import rachmanforniandi.celestialnews.data.model.Article

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Query("SELECT * FROM articles")
    fun getAllArticleNews():Flow<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)

}
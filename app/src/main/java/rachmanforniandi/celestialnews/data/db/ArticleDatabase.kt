package rachmanforniandi.celestialnews.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rachmanforniandi.celestialnews.data.model.Article

@Database(entities = [Article::class],
version = 1,
exportSchema = false)

@TypeConverters(Converters::class)
abstract class ArticleDatabase :RoomDatabase(){
    abstract fun getArticleDao():NewsDao
}
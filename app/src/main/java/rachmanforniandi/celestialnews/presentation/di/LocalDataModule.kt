package rachmanforniandi.celestialnews.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import rachmanforniandi.celestialnews.data.db.NewsDao
import rachmanforniandi.celestialnews.domain.repository.dataSource.NewsLocalDataSource
import rachmanforniandi.celestialnews.domain.repository.dataSourceImpl.NewsLocalDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Singleton
    @Provides
    fun provideLocalDataSource(newsDao: NewsDao):NewsLocalDataSource{
        return NewsLocalDataSourceImpl(newsDao)
    }
}
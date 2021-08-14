package rachmanforniandi.celestialnews.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import rachmanforniandi.celestialnews.domain.repository.NewsRepository
import rachmanforniandi.celestialnews.domain.repository.NewsRepositoryImpl
import rachmanforniandi.celestialnews.domain.repository.dataSource.NewsLocalDataSource
import rachmanforniandi.celestialnews.domain.repository.dataSource.NewsRemoteDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource,
    newsLocalDataSource: NewsLocalDataSource):NewsRepository{
        return NewsRepositoryImpl(newsRemoteDataSource,newsLocalDataSource)
    }
}
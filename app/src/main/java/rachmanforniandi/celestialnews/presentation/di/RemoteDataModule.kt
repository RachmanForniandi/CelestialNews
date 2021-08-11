package rachmanforniandi.celestialnews.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import rachmanforniandi.celestialnews.data.networkConfig.NewsApiService
import rachmanforniandi.celestialnews.domain.repository.dataSource.NewsRemoteDataSource
import rachmanforniandi.celestialnews.domain.repository.dataSourceImpl.NewsRemoteDataSourceImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(
        newsApiService: NewsApiService
    ):NewsRemoteDataSource{
        return NewsRemoteDataSourceImpl(newsApiService)
    }
}
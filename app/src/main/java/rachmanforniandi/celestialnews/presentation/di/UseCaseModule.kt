package rachmanforniandi.celestialnews.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import rachmanforniandi.celestialnews.domain.repository.NewsRepository
import rachmanforniandi.celestialnews.domain.usecase.GetNewsHeadlineUseCase
import rachmanforniandi.celestialnews.domain.usecase.GetSearchedNewsUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetNewsHeadlinesUseCase(
        newsRepository: NewsRepository
    ):GetNewsHeadlineUseCase{
        return GetNewsHeadlineUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun provideGetSearchedNewsUseCase(
        newsRepository: NewsRepository
    ):GetSearchedNewsUseCase{
        return GetSearchedNewsUseCase(newsRepository)
    }


}
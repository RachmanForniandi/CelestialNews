package rachmanforniandi.celestialnews.presentation.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import rachmanforniandi.celestialnews.domain.usecase.GetNewsHeadlineUseCase
import rachmanforniandi.celestialnews.presentation.viewmodels.NewsViewModelFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideNewsViewModelFactory(application: Application,
                                    getNewsHeadlineUseCase: GetNewsHeadlineUseCase):NewsViewModelFactory{
        return NewsViewModelFactory(application,getNewsHeadlineUseCase)
    }
}
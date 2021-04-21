package rachmanforniandi.celestialnews.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import rachmanforniandi.celestialnews.domain.usecase.GetNewsHeadlineUseCase

class NewsViewModelFactory(private val app: Application,
                           val getNewsHeadlineUseCase: GetNewsHeadlineUseCase):ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(app, getNewsHeadlineUseCase)as T
    }

}
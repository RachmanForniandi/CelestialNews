package rachmanforniandi.celestialnews.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import rachmanforniandi.celestialnews.R
import rachmanforniandi.celestialnews.databinding.ActivityMainBinding
import rachmanforniandi.celestialnews.presentation.viewmodels.NewsViewModel
import rachmanforniandi.celestialnews.presentation.viewmodels.NewsViewModelFactory
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    @Inject
    lateinit var factory: NewsViewModelFactory
    lateinit var viewModel:NewsViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bnvNews.setupWithNavController(
            fragment_main.findNavController()
        )
        viewModel = ViewModelProvider(this,factory).get(NewsViewModel::class.java)
    }
}
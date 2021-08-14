package rachmanforniandi.celestialnews.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import rachmanforniandi.celestialnews.R
import rachmanforniandi.celestialnews.databinding.FragmentInfoBinding
import rachmanforniandi.celestialnews.presentation.viewmodels.NewsViewModel
import rachmanforniandi.celestialnews.ui.activity.MainActivity

class InfoFragment : Fragment() {
    private  lateinit var viewModel: NewsViewModel
    private lateinit var fragmentInfoBinding: FragmentInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentInfoBinding = FragmentInfoBinding.bind(view)
        val args: InfoFragmentArgs by navArgs()
        val article = args.selectedArticle
        viewModel = (activity as MainActivity).viewModel

        fragmentInfoBinding.wvInfoDetail.apply {
            webViewClient = WebViewClient()
            if (article.url!=null){
                loadUrl(article.url)
                println("testUrl"+article.url)
            }
        }

        fragmentInfoBinding.imgSaveNews.setOnClickListener {
            fragmentInfoBinding.imgSaveNews.setImageDrawable(ContextCompat.getDrawable(requireActivity(),R.drawable.ic_star_on))
            viewModel.saveArticle(article)
            Snackbar.make(view,"Saved News Successfully!",Snackbar.LENGTH_LONG).show()
        }
    }

}
package rachmanforniandi.celestialnews.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import rachmanforniandi.celestialnews.R
import rachmanforniandi.celestialnews.adapter.NewsAdapter
import rachmanforniandi.celestialnews.databinding.FragmentNewsBinding
import rachmanforniandi.celestialnews.presentation.viewmodels.NewsViewModel
import rachmanforniandi.celestialnews.ui.activity.MainActivity

class NewsFragment : Fragment() {

    private  lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var fragmentNewsBinding: FragmentNewsBinding
    private var country = "id"
    private var page = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentNewsBinding = FragmentNewsBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        initListDataNews()
        viewNewsList()
    }

    private fun viewNewsList(){
        viewModel.getNewsHeadLines(country,page)
        viewModel.newsHeadLines.observe(viewLifecycleOwner,{ response->
            when(response){
                is rachmanforniandi.celestialnews.helper.Resource.Success->{
                    hideProgressBar()
                    response.data.let {
                        Log.i("MYTAG","came here ${it?.articles?.toList()?.size}")
                        newsAdapter.differ.submitList(it?.articles?.toList())
                    }
                    Log.e("testResponse",""+response.data)
                }
                is rachmanforniandi.celestialnews.helper.Resource.Error->{
                    hideProgressBar()
                    response.message.let {
                        Toast.makeText(activity,"Terjadi error: $it",Toast.LENGTH_LONG).show()
                    }
                }
                is rachmanforniandi.celestialnews.helper.Resource.Loading->{
                    showProgressBar()
                }
            }
        })
    }

    private fun initListDataNews() {
        newsAdapter = NewsAdapter()
        fragmentNewsBinding.rvNews.apply {

            adapter = newsAdapter
            //layoutManager = LinearLayoutManager(activity)
        }
        fragmentNewsBinding.rvNews.setHasFixedSize(true)
    }

    private fun showProgressBar(){
        fragmentNewsBinding.pgNews.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        fragmentNewsBinding.pgNews.visibility = View.INVISIBLE
    }


}
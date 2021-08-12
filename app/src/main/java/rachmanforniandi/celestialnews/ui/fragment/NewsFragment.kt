package rachmanforniandi.celestialnews.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import rachmanforniandi.celestialnews.R
import rachmanforniandi.celestialnews.adapter.NewsAdapter
import rachmanforniandi.celestialnews.databinding.FragmentNewsBinding
import rachmanforniandi.celestialnews.presentation.viewmodels.NewsViewModel
import rachmanforniandi.celestialnews.ui.activity.MainActivity

class NewsFragment : Fragment(R.layout.fragment_news) {

    private  lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var fragmentNewsBinding: FragmentNewsBinding
    private var country = "id"
    private var page = 1
    private var pages =0
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
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
        newsAdapter = (activity as MainActivity).newsAdapter
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_article",it)
            }
            findNavController().navigate(
                R.id.action_newsFragment_to_infoFragment,bundle
            )
            println("testBundle"+ bundle)
        }
        initListDataNews()
        viewNewsList()

    }

    private fun viewNewsList(){
        viewModel.getNewsHeadLines(country,page)
        viewModel.newsHeadLines.observe(viewLifecycleOwner,{ response->
            when(response){
                is rachmanforniandi.celestialnews.helper.Resource.Success->{

                    hideProgressBar()
                    response.data?.let {
                        Log.i("MYTAG","came here ${it.articles.toList().size}")
                        newsAdapter.differ.submitList(it.articles.toList())
                        if (it.totalResults%20 ==0){
                            pages = it.totalResults / 20
                        }else{
                            pages = it.totalResults/20+1
                        }
                        isLastPage = page == pages
                    }
                    Log.e("testResponse",""+response.data)
                }
                is rachmanforniandi.celestialnews.helper.Resource.Error->{
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity,"Terjadi error: $it",Toast.LENGTH_LONG).show()
                    }
                    Log.e("response_msg_2",""+response)
                }
                is rachmanforniandi.celestialnews.helper.Resource.Loading->{
                    showProgressBar()
                }
                //Log.e("response_msg_3",""+response.message)

            }
            //Log.e("response_msg",""+response)
            //Log.e("response_data",""+response.data)
        })
    }

    private fun initListDataNews() {
        newsAdapter = NewsAdapter()
        fragmentNewsBinding.rvNews.apply {

            adapter = newsAdapter
            //layoutManager= LinearLayoutManager(activity)
            addOnScrollListener(this@NewsFragment.onScrollListener)
        }
        fragmentNewsBinding.rvNews.setHasFixedSize(true)
    }

    private fun showProgressBar(){
        isLoading = true
        fragmentNewsBinding.pgNews.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        isLoading = false
        fragmentNewsBinding.pgNews.visibility = View.GONE
    }

    private val onScrollListener = object:RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
               isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = fragmentNewsBinding.rvNews.layoutManager as LinearLayoutManager
            val sizeOfTheCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstCompletelyVisibleItemPosition()

            val hasReachedToEnd = topPosition+visibleItems >= sizeOfTheCurrentList
            val shouldPaginate = !isLoading && !isLastPage && hasReachedToEnd && isScrolling
            if (shouldPaginate){
                page++
                viewModel.getNewsHeadLines(country, page)
                isScrolling = false
            }
        }
    }


}
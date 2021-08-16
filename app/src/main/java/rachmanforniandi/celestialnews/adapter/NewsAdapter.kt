package rachmanforniandi.celestialnews.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exianewsv3.helpers.formNewsApiDate
import rachmanforniandi.celestialnews.data.model.Article
import rachmanforniandi.celestialnews.databinding.ItemNewsBinding

class NewsAdapter:RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){

    private val callback = object:DiffUtil.ItemCallback<Article>(){

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding
                .inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = differ.currentList[position]
        //holder.binding.cvNews.setOnClickListener()
        holder.bind(news)
        holder.itemView.setOnClickListener { view->
            onItemClickListener?.let {
                it(news)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class NewsViewHolder (val binding:ItemNewsBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(article: Article){
            Log.i("MYTAG","came here ${article.title}")
            val formatDateNews = article.publishedAt.let { formNewsApiDate(it) }
            binding.txtNewsTitle.text = article.title
            binding.txtNewsContent.text = article.description
            binding.txtNewsTime.text = "$formatDateNews"
            binding.txtSourceNews.text = article.source?.name

            Glide.with(binding.imgNews.context)
                .load(article.urlToImage)
                .into(binding.imgNews)

            /*binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(article)
                }
            }*/

        }
    }

    private var onItemClickListener:((Article)->Unit)?=null

    fun setOnItemClickListener(listener:(Article)->Unit){
        onItemClickListener = listener
    }

}
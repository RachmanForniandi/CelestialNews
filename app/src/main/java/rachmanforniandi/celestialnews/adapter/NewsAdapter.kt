package rachmanforniandi.celestialnews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.exianewsv3.helpers.formNewsApiDate
import rachmanforniandi.celestialnews.data.model.Article
import rachmanforniandi.celestialnews.databinding.ItemNewsBinding

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){

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
        holder.bind(news)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class NewsViewHolder (val view:ItemNewsBinding):RecyclerView.ViewHolder(view.root){
        fun bind(article: Article){
            view.txtNewsTitle.text = article.title
            view.txtNewsContent.text = article.description.let { formNewsApiDate(it) }
            view.txtNewsTime.text = article.publishedAt
            view.txtSourceNews.text = article.source?.name

        }
    }

}
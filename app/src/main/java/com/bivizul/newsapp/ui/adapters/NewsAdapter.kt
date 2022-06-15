package com.bivizul.newsapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bivizul.newsapp.databinding.ItemArticleBinding
import com.bivizul.newsapp.models.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

//    inner class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view)
    inner class NewsViewHolder(val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root)

    private val callback = object : DiffUtil.ItemCallback<Article>() {

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

//    private var onItemClickListener: ((Article) -> Unit)? = null
//
//    fun setOnItemClickListener(listener: (Article) -> Unit) {
//        onItemClickListener = listener
//    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsViewHolder {
        val binding = ItemArticleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsViewHolder(binding)
//        return NewsViewHolder(
//            LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
//        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            with(holder.binding) {
//                com.bumptech.glide.Glide.with(findViewById(R.id.mainFragment)).load(article.urlToImage).into(imgItemArticle)
                imgItemArticle.load(article.urlToImage)
                imgItemArticle.clipToOutline = true
                tvArticleDate.text = article.publishedAt
                tvArticleText.text = article.title
            }

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}
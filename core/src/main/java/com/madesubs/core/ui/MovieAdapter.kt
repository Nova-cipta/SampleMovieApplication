package com.madesubs.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.madesubs.core.R
import com.madesubs.core.databinding.ItemMovieBinding
import com.madesubs.core.domain.model.Movie

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit?)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMovieBinding.bind(itemView)
        fun bind(data: Movie) {
            val urlBackdrop = "https://image.tmdb.org/t/p/original/" + data.backdropPath
            with(binding) {
                Glide.with(itemView.context).load(urlBackdrop).into(ivBackdrop)
                tvTitle.text = data.title
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}
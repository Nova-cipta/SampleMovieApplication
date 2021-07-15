package com.madesubs.myapplication.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.madesubs.core.domain.model.Movie
import com.madesubs.myapplication.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private val viewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        showDetailMovie(detailMovie)
    }

    private fun showDetailMovie(movie: Movie?) {
        movie?.let {
            supportActionBar?.title = movie.title
            binding.content.tvDetailDescription.text = movie.overview
            binding.content.popularity.text = StringBuilder("Popularity: " + movie.popularity)
            binding.content.vote.text = StringBuilder("Score: " + movie.voteAverage)
            binding.content.progressBar.max = 100
            binding.content.progressBar.progress =
                movie.voteAverage.take(1).toInt() * 10 + movie.voteAverage.takeLast(1).toInt()
            binding.content.releaseDate.text = movie.releaseDate

            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/original/" + movie.posterPath)
                .into(binding.content.ivPoster)
            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/original/" + movie.backdropPath)
                .into(binding.ivDetailBackdrop)

            var statusFavorite = movie.isFavorite
            binding.fab.isSelected = statusFavorite
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                viewModel.setFavoriteMovie(movie, statusFavorite)
                binding.fab.isSelected = statusFavorite
            }
        }
    }
}
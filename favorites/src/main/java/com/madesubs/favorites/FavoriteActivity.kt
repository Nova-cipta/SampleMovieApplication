package com.madesubs.favorites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.madesubs.core.ui.MovieAdapter
import com.madesubs.favorites.di.favoriteModule
import com.madesubs.favorites.databinding.ActivityFavoriteBinding
import com.madesubs.myapplication.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val viewModel: FavoriteViewModel by viewModel()

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.title = "Favorites Movie"

        loadKoinModules(favoriteModule)

        favAdapter = MovieAdapter()
        favAdapter.onItemClick = { item ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, item)
            startActivity(intent)
        }

        viewModel.favoriteMovie.observe(this, { dataFav ->
            favAdapter.setData(dataFav)
            binding.layoutFav.empty.root.visibility = if (dataFav.isNotEmpty()) View.GONE else View.VISIBLE
        })

        with(binding.layoutFav.rvFavorite) {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            setHasFixedSize(true)
            adapter = favAdapter
        }
    }
}
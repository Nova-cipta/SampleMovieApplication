package com.madesubs.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.madesubs.core.ui.MovieAdapter
import com.madesubs.favorite.databinding.ActivityFavoriteBinding
import com.madesubs.favorite.di.favoriteModule
import com.madesubs.myapplication.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private val viewModel: FavoriteViewModel by viewModel()

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            binding.layout.viewEmpty.root.visibility =
                if (dataFav.isNotEmpty()) View.GONE else View.VISIBLE
        })

        with(binding.layout.rvFavorite) {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            setHasFixedSize(true)
            adapter = favAdapter
        }
    }
}
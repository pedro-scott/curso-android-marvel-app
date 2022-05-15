package com.example.marvelapp.presentation

import android.os.Bundle
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.marvelapp.R
import com.example.marvelapp.databinding.ActivityMainBinding
import com.example.marvelapp.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    inflater = ActivityMainBinding::inflate
) {
    private val appBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(
                R.id.heroesFragment,
                R.id.favoritesFragment,
                R.id.aboutFragment,
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setView()
        setNavigationListener()
    }

    private fun setView() = binding.run {
        mainBottomNav.setupWithNavController(findNavController(R.id.mainNavHost))
        mainTopAppBar.setupWithNavController(
            findNavController(R.id.mainNavHost),
            appBarConfiguration
        )
    }

    private fun setNavigationListener() {
        findNavController(R.id.mainNavHost).addOnDestinationChangedListener { _, destination, _ ->
            appBarConfiguration.topLevelDestinations.contains(destination.id).let { isTopLevelDestination ->
                if (!isTopLevelDestination)
                    binding.mainTopAppBar.setNavigationIcon(R.drawable.ic_back)
            }
        }
    }
}
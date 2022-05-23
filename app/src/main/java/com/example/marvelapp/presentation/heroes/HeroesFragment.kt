package com.example.marvelapp.presentation.heroes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.example.domain.entities.heroes.Hero
import com.example.marvelapp.databinding.FragmentHeroesBinding
import com.example.marvelapp.presentation.base.BaseFragment
import com.example.marvelapp.presentation.heroes.adapter.HeroesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HeroesFragment : BaseFragment<FragmentHeroesBinding>(
    inflater = FragmentHeroesBinding::inflate
) {

    private val viewModel by viewModels<HeroesViewModel>()

    @Inject
    lateinit var heroesAdapter: HeroesAdapter

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        setObserver()
        viewModel.handle(HeroesIntent.LoadHeroes(""))
    }

    private fun setAdapter() {
        binding.heroesListRv.apply {
            setHasFixedSize(true)
            adapter = heroesAdapter
        }
    }

    private fun setObserver() {
        lifecycleScope.launch {
            viewModel.state
                .flowWithLifecycle(lifecycle)
                .collect { state -> handleState(state) }
        }
    }

    private fun handleState(state: HeroesState) {
        when (state) {
            is HeroesState.InitialState -> Unit
            is HeroesState.ShowHeroes -> updateHeroesList(state.heroesFlow)
        }
    }

    private fun updateHeroesList(heroesFlow: Flow<PagingData<Hero>>) {
        lifecycleScope.launch {
            heroesFlow
                .flowWithLifecycle(lifecycle)
                .collectLatest { pagingData -> heroesAdapter.submitData(pagingData) }
        }
    }
}
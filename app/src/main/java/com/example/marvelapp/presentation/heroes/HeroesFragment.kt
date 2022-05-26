package com.example.marvelapp.presentation.heroes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.heroes.Hero
import com.example.marvelapp.databinding.FragmentHeroesBinding
import com.example.marvelapp.presentation.base.BaseFragment
import com.example.marvelapp.presentation.heroes.adapter.HeroesAdapter
import com.example.marvelapp.presentation.heroes.adapter.HeroesLoadStateAdapter
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
        setListeners()
        setObserver()
        setObserverToLoadState()
        viewModel.handle(HeroesIntent.LoadHeroes(""))
    }

    private fun setAdapter() {
        binding.heroesListRv.apply {
            getRecyclerView().setHasFixedSize(true)
            setAdapter(
                heroesAdapter.withLoadStateHeaderAndFooter(
                    header = HeroesLoadStateAdapter(heroesAdapter::retry),
                    footer = HeroesLoadStateAdapter(heroesAdapter::retry)
                )
            )
            setLayoutManager(
                LinearLayoutManager(
                    requireContext(),
                    RecyclerView.VERTICAL,
                    false
                )
            )
            addVeiledItems(VEILED_ITEMS_COUNT)
        }
    }

    private fun setListeners() {
        binding.run {
            heroesErrorLayout.errorTryAgainBtn.setOnClickListener {
                viewModel.handle(HeroesIntent.RefreshList)
            }
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
            is HeroesState.HeroesLoading -> displayHeroesLoading(state.isLoading)
            is HeroesState.HeroesError -> displayHeroesError()
            is HeroesState.HeroesRefresh -> refreshHeroes()
        }
    }

    private fun setObserverToLoadState() {
        lifecycleScope.launch {
            heroesAdapter.loadStateFlow
                .flowWithLifecycle(lifecycle)
                .collectLatest { loadState -> handleLoadState(loadState.refresh) }
        }
    }

    private fun handleLoadState(loadState: LoadState) {
        when (loadState) {
            is LoadState.Loading -> HeroesIntent.RefreshLoading(true)
            is LoadState.NotLoading -> HeroesIntent.RefreshLoading(false)
            is LoadState.Error -> HeroesIntent.RefreshError
        }.let { intent -> viewModel.handle(intent) }
    }

    private fun updateHeroesList(heroesFlow: Flow<PagingData<Hero>>) {
        lifecycleScope.launch {
            heroesFlow
                .flowWithLifecycle(lifecycle)
                .collectLatest { pagingData -> heroesAdapter.submitData(pagingData) }
        }
    }

    private fun displayHeroesLoading(isLoading: Boolean) {
        binding.run {
            heroesListRv.apply {
                if (isLoading) {
                    heroesFlipper.displayedChild = FLIPPER_CHILD_DEFAULT
                    veil()
                } else unVeil()
            }
        }
    }

    private fun displayHeroesError() {
        displayHeroesLoading(false)
        binding.heroesFlipper.displayedChild = FLIPPER_CHILD_ERROR
    }

    private fun refreshHeroes() {
        heroesAdapter.refresh()
    }

    companion object {
        // SHIMMER
        private const val VEILED_ITEMS_COUNT = 5

        // VIEW FLIPPER
        private const val FLIPPER_CHILD_DEFAULT = 0
        private const val FLIPPER_CHILD_ERROR = 1
    }
}
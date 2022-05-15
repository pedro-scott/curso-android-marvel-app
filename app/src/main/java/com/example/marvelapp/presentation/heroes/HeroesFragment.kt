package com.example.marvelapp.presentation.heroes

import android.os.Bundle
import android.view.View
import com.example.domain.entities.heroes.Hero
import com.example.marvelapp.databinding.FragmentHeroesBinding
import com.example.marvelapp.presentation.base.BaseFragment
import com.example.marvelapp.presentation.heroes.adapter.HeroesAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HeroesFragment : BaseFragment<FragmentHeroesBinding>(
    inflater = FragmentHeroesBinding::inflate
) {

    @Inject
    lateinit var heroesAdapter: HeroesAdapter

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
    }

    private fun setAdapter() {
        binding.heroesListRv.apply {
            setHasFixedSize(true)
            adapter = heroesAdapter
        }
    }
}
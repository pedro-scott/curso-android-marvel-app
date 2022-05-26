package com.example.marvelapp.presentation.heroes.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class HeroesLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<HeroesLoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): HeroesLoadStateViewHolder =
        HeroesLoadStateViewHolder.create(
            parent = parent,
            retry = retry
        )

    override fun onBindViewHolder(
        holder: HeroesLoadStateViewHolder,
        loadState: LoadState
    ) { holder.bind(loadState) }
}
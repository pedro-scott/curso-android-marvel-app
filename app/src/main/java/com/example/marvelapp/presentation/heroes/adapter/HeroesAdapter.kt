package com.example.marvelapp.presentation.heroes.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.entities.heroes.Hero
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class HeroesAdapter @Inject constructor() : ListAdapter<Hero, HeroesViewHolder>(diffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HeroesViewHolder = HeroesViewHolder.create(parent)

    override fun onBindViewHolder(
        holder: HeroesViewHolder,
        position: Int
    ) { holder.bind(getItem(position)) }

    companion object {
        private val diffCallback =
            object : DiffUtil.ItemCallback<Hero>() {
                override fun areItemsTheSame(
                    oldItem: Hero,
                    newItem: Hero
                ): Boolean =
                    oldItem.name == newItem.name &&
                    oldItem.imagerUrl == newItem.imagerUrl

                override fun areContentsTheSame(
                    oldItem: Hero,
                    newItem: Hero
                ): Boolean = oldItem == newItem
            }
    }
}
package com.example.marvelapp.presentation.heroes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.R
import com.example.marvelapp.databinding.ItemLoadStateLayoutBinding
import com.example.marvelapp.util.extension.setHtmlText
import com.example.marvelapp.util.extension.setVisibility

class HeroesLoadStateViewHolder(
    private val binding: ItemLoadStateLayoutBinding,
    private val retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(loadState: LoadState) {
        binding.run {
            loadStateErrorTv.setHtmlText(itemView.context.getString(R.string.error_loading_more_try_again))

            loadStateErrorTv.setVisibility(
                isVisible = loadState is LoadState.Error,
                setInvisible = true
            )
            loadStateLoadingPb.setVisibility(loadState is LoadState.Loading)

            loadStateErrorTv.setOnClickListener { retry() }
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            retry: () -> Unit
        ): HeroesLoadStateViewHolder =
            LayoutInflater.from(parent.context).let { inflater ->
                ItemLoadStateLayoutBinding.inflate(
                    inflater,
                    parent,
                    false
                ).let { binding ->
                    HeroesLoadStateViewHolder(
                        binding = binding,
                        retry = retry
                    )
                }
            }
    }
}
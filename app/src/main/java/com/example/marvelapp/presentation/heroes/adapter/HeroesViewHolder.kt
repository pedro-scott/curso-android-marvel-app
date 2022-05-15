package com.example.marvelapp.presentation.heroes.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.domain.entities.heroes.Hero
import com.example.marvelapp.R
import com.example.marvelapp.databinding.ItemHeroLayoutBinding
import com.example.marvelapp.util.extension.setVisibility

class HeroesViewHolder(
    private val binding: ItemHeroLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val glideListener: RequestListener<Drawable> =
        object : RequestListener<Drawable> {
            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                binding.heroImageLoading.setVisibility(false)
                return false
            }

            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                binding.heroImageLoading.setVisibility(false)
                return false
            }
        }

    fun bind(hero: Hero) {
        binding.run {
            heroNameTv.text = hero.name
            Glide.with(itemView)
                .load(hero.imagerUrl)
                .centerCrop()
                .listener(glideListener)
                .error(R.drawable.ic_img_loading_error)
                .into(heroBannerIv)
        }
    }

    companion object {
        fun create(parent: ViewGroup): HeroesViewHolder =
            LayoutInflater.from(parent.context).let { inflater ->
                ItemHeroLayoutBinding.inflate(
                    inflater,
                    parent,
                    false
                ).let { binding ->
                    HeroesViewHolder(binding)
                }
            }
    }
}
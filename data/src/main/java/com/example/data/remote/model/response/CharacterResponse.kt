package com.example.data.remote.model.response

import com.example.domain.entities.heroes.Hero

data class CharacterResponse(
    val id: String,
    val name: String,
    val thumbnail: ThumbnailResponse
) {
    fun toHero() =
        Hero(
            name = name,
            imagerUrl = thumbnail.getImageUrl()
        )
}
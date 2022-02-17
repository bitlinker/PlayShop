package com.github.bitlinker.playshop.model.gamecard

import androidx.annotation.DrawableRes

data class GameCardModel(
    val id: String,
    @DrawableRes val backgroundRes: Int,
    @DrawableRes val logoRes: Int,
    val title: String,
    val rating: Float,
    val formattedReviewsCount: String,
    val tags: List<String>,
    val description: String,
    val media: List<Media>,
    val reviews: List<Review>
) {
    data class Media(
        val id: String,
        @DrawableRes val previewRes: Int,
        val isPlayable: Boolean = false,
    )

    data class Review(
        val id: String,
        @DrawableRes val avatarRes: Int,
        val author: String,
        val formattedDate: String,
        val text: String,
    )
}

package com.github.bitlinker.playshop.model.gamecard

import com.github.bitlinker.playshop.R

internal object GameCardSampleData {
    val sampleGameInfoModel = GameCardModel(
        id = "100500",
        backgroundRes = R.drawable.background,
        logoRes = R.drawable.logo,
        title = "DoTA 2",
        rating = 4.3F,
        formattedReviewsCount = "70M",
        tags = listOf("Moba", "Multiplayer", "Strategy"),
        description = "Dota 2 is a multiplayer online battle arena (MOBA) game which has two teams of five players compete to collectively destroy a large structure defended by the opposing team known as the \"Ancient\", whilst defending their own.",
        media = listOf(
            GameCardModel.Media(
                id = "1",
                previewRes = R.drawable.media1,
                isPlayable = true
            ),
            GameCardModel.Media(
                id = "2",
                previewRes = R.drawable.media1,
            ),
            GameCardModel.Media(
                id = "3",
                previewRes = R.drawable.media2
            ),
        ),
        reviews = listOf(
            GameCardModel.Review(
                id = "1",
                avatarRes = R.drawable.background, // TODO
                author = "Auguste Conte",
                formattedDate = "February 14, 2019",
                text = "“Once you start to learn its secrets, there’s a wild and exciting variety of play here that’s unmatched, even by its peers.”", // TODO: copy quotes
            ),
            GameCardModel.Review(
                id = "2",
                avatarRes = R.drawable.background, // TODO
                author = "Jang Marcelino",
                formattedDate = "February 14, 2019",
                text = "“Once you start to learn its secrets, there’s a wild and exciting variety of play here that’s unmatched, even by its peers.”",
            ),
            GameCardModel.Review(
                id = "3",
                avatarRes = R.drawable.background,
                author = "Jang Marcelino",
                formattedDate = "February 14, 2019",
                text = "“Once you start to learn its secrets, there’s a wild and exciting variety of play here that’s unmatched, even by its peers.”",
            )
        )
    )
}
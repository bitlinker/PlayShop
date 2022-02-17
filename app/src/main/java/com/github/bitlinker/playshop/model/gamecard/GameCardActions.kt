package com.github.bitlinker.playshop.model.gamecard

sealed class GameCardAction {
    object GoBackClicked : GameCardAction()
    object MoreClicked : GameCardAction()
    data class PlayPauseClicked(val mediaId: String) : GameCardAction()
    object InstallClicked : GameCardAction()
}

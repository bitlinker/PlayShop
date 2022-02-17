package com.github.bitlinker.playshop.model.gamecard

interface GameCardDispatcher {
    fun dispatch(action: GameCardAction)

    companion object {
        val Empty = object : GameCardDispatcher {
            override fun dispatch(action: GameCardAction) = Unit
        }
    }
}
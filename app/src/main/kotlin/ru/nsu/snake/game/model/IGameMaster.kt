package ru.nsu.snake.game.model

interface IGameMaster {

    fun doGameTick()
    fun addNewPlayer(player : IPlayer)
}
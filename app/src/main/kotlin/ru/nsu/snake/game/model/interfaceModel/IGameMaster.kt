package ru.nsu.snake.game.model.interfaceModel

interface IGameMaster {

    fun doGameTick()
    fun addNewPlayer(player : IPlayer)
}
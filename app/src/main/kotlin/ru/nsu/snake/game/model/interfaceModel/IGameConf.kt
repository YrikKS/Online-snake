package ru.nsu.snake.game.model.interfaceModel

import ru.nsu.snake.game.model.gameObjects.interfaceObjects.IMapConfig

interface IGameConf {
    val listPlayers: MutableList<IPlayer>
    var mapConfig: IMapConfig
    var canJoin: Boolean?
    var gameName: String
}
package ru.nsu.snake.game.model.gameObjects.interfaceObjects

import ru.nsu.snake.game.model.gameObjects.enumForGameObjects.ModelDirection

interface ICoord : IMapObject {
    var x: Int
    var y: Int
    fun moveCoord(modelDirection: ModelDirection)
    fun coordEquals(other: ICoord) : Boolean
    fun moveThroughWalls(mapConfig: IMapConfig)
    fun newCopy() : ICoord
}
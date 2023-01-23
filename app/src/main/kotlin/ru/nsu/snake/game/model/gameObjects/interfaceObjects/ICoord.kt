package ru.nsu.snake.game.model.gameObjects.interfaceObjects

import ru.nsu.snake.game.model.gameObjects.enumForGameObjects.Direction

interface ICoord : IMapObject {
    var x: Int
    var y: Int
    fun moveCoord(direction: Direction)
    fun coordEquals(other: ICoord) : Boolean
    fun newCopy() : ICoord
}
package ru.nsu.snake.game.model.gameObjects.interfaceObjects

import ru.nsu.snake.game.model.gameObjects.enumForGameObjects.Direction

interface ISnake : IMapObject {
    var listOfField: MutableList<ICoord>
    var _direction: Direction
        set
    val previousCoordTip: ICoord
    fun move()
    fun grow()
}
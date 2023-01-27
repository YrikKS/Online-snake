package ru.nsu.snake.game.model.gameObjects.interfaceObjects

import ru.nsu.snake.game.model.gameObjects.enumForGameObjects.ModelDirection

interface ISnake : IMapObject {
    var listOfField: MutableList<ICoord>
    var modelDirection: ModelDirection
        set
    val previousCoordTip: ICoord
    fun move()
    fun grow()
}
package ru.nsu.snake.game.model.gameObjects.interfaceObjects

import ru.nsu.snake.game.model.gameObjects.enumForGameObjects.StatusGameObj
import ru.nsu.snake.game.model.gameObjects.implObjects.GameMap

interface IMapObject {
    var statusGameObj: StatusGameObj
    fun checkEvents(gameMap: IMapGame)
    fun checkSnakeFoodEvents(gameMap: IMapGame)
    fun checkSnakeHitEvents(gameMap: IMapGame)
}
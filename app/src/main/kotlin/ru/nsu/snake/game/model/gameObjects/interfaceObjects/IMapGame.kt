package ru.nsu.snake.game.model.gameObjects.interfaceObjects

import ru.nsu.snake.game.model.gameObjects.enumForGameObjects.TypeField

interface IMapGame {
    val map: Array<Array<MutableList<TypeField>>>
    val mapConfig: IMapConfig
    fun clearMap()
    fun addOnMap(gameObject: IMapObject)
    fun printMap()
}
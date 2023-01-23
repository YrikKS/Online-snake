package ru.nsu.snake.game.model.gameObjects.interfaceObjects

import ru.nsu.snake.game.model.gameObjects.enumForGameObjects.TypeField

interface IMapGame {
    val sizeX: Int
    val sizeY: Int
    val gameSpeed: Int
    val countFood: Int
    val map : Array<Array<MutableList<TypeField>>>

    fun clearMap()
    fun addOnMap(gameObject: IMapObject)
    fun printMap()
}
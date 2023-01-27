package ru.nsu.snake.game.model.gameObjects.implObjects

import ru.nsu.snake.game.model.gameObjects.enumForGameObjects.TypeField
import ru.nsu.snake.game.model.gameObjects.interfaceObjects.IMapConfig
import ru.nsu.snake.game.model.gameObjects.interfaceObjects.IMapGame
import ru.nsu.snake.game.model.gameObjects.interfaceObjects.IMapObject

data class GameMap(
    override val mapConfig: IMapConfig
) : IMapGame {

    override val map = Array(mapConfig.sizeY) {
        Array(mapConfig.sizeX) {
            mutableListOf(TypeField.EMPTY)
        }
    }

    override fun printMap() {
        map.forEach {
            it.forEach { it2 ->
                var code = 0
                it2.forEach { it3 ->
                    when (it3) {
                        TypeField.FOOD -> code += 10
                        TypeField.SNAKE -> code += 100
                        else -> {}
                    }
                }
                when (code) {
                    in 0..0 -> print("__")
                    in 10..10 -> print("F1")
                    in 100..100 -> print("S1")
                    in 110..110 -> print("SF")
                    in 200..200 -> print("S2")
                }
                print(" ")
            }
            println()
        }
        println("   ------------       -------------      -------------")
    }

    override fun clearMap() {
        for (element in map) {
            for (j in 0 until map.random().size) {
                element[j].removeIf { it != TypeField.EMPTY }
            }
        }
    }

    override fun addOnMap(gameObject: IMapObject) {
        when (gameObject) {
            is AbstractSnake -> {
                gameObject.listOfField.forEach {
                    map[it.y][it.x].add(TypeField.SNAKE)
                }
            }
            is AbstractFood -> {
                map[gameObject.coord.y][gameObject.coord.x].add(TypeField.FOOD)
            }
            is AbstractCoord -> {
                map[gameObject.y][gameObject.x].add(TypeField.SNAKE)
            }
            else -> {}
        }
    }
}
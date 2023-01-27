package ru.nsu.snake.game.model.gameObjects.implObjects

import ru.nsu.snake.game.model.gameObjects.enumForGameObjects.ModelDirection
import ru.nsu.snake.game.model.gameObjects.interfaceObjects.ICoord
import ru.nsu.snake.game.model.gameObjects.interfaceObjects.IMapConfig

abstract class AbstractCoord : MapObject(), ICoord

data class Coord(override var x: Int, override var y: Int) : AbstractCoord() {
    override fun moveCoord(modelDirection: ModelDirection) {
        when (modelDirection) {
            ModelDirection.UP -> y -= 1
            ModelDirection.DOWN -> y += 1
            ModelDirection.LEFT -> x -= 1
            ModelDirection.RIGHT -> x += 1
        }
    }

    override fun coordEquals(other: ICoord) = (x == other.x && y == other.y)

    override fun newCopy(): ICoord = Coord(x, y)

    override fun moveThroughWalls(mapConfig: IMapConfig) {
        when {
            y < 0 -> {
                y = mapConfig.sizeY - 1
            }
            y >= mapConfig.sizeY -> {
                y = 0
            }
        }
        when {
            x < 0 -> {
                x = mapConfig.sizeX - 1
            }
            x >= mapConfig.sizeX -> {
                x = 0
            }
        }
    }
}
package ru.nsu.snake.game.model.gameObjects.implObjects

import ru.nsu.snake.game.model.gameObjects.enumForGameObjects.Direction
import ru.nsu.snake.game.model.gameObjects.interfaceObjects.ICoord

abstract class AbstractCoord : MapObject(), ICoord

data class Coord(override var x: Int, override var y: Int) : AbstractCoord() {
    override fun moveCoord(direction: Direction) {
        when (direction) {
            Direction.UP -> y -= 1
            Direction.DOWN -> y += 1
            Direction.LEFT -> x -= 1
            Direction.RIGHT -> x += 1
        }
    }

    override fun coordEquals(other: ICoord) = (x == other.x && y == other.y)

    override fun newCopy(): ICoord = Coord(x, y)
}
package ru.nsu.snake.game.model.gameObjects.implObjects

import ru.nsu.snake.Constants
import ru.nsu.snake.game.model.gameObjects.enumForGameObjects.Direction
import ru.nsu.snake.game.model.gameObjects.interfaceObjects.ICoord
import ru.nsu.snake.game.model.gameObjects.interfaceObjects.IMapGame
import ru.nsu.snake.game.model.gameObjects.interfaceObjects.ISnake

abstract class AbstractSnake : MapObject(), ISnake

class Snake(
    direction: Direction,
    private val map: IMapGame,
    override var listOfField: MutableList<ICoord>
) :
    AbstractSnake() {
    override var _direction: Direction = direction
        set(value) {
            val newCoordAfterMove = listOfField[Constants.INDEX_HEAD_SNAKE].newCopy().also {
                it.moveCoord(value)
                moveThroughWalls(it)
            }
            field =
                if (newCoordAfterMove.coordEquals(listOfField[Constants.INDEX_SECOND_BLOCK_SNAKE])) {
                    _direction
                } else {
                    value
                }
        }

    init {
        val tipSnake = Coord(
            listOfField[Constants.INDEX_HEAD_SNAKE].x,
            listOfField[Constants.INDEX_HEAD_SNAKE].y
        )
        when (direction) {
            Direction.UP -> listOfField.add(tipSnake.apply { moveCoord(Direction.DOWN) })
            Direction.DOWN -> listOfField.add(tipSnake.apply { moveCoord(Direction.UP) })
            Direction.LEFT -> listOfField.add(tipSnake.apply { moveCoord(Direction.RIGHT) })
            Direction.RIGHT -> listOfField.add(tipSnake.apply { moveCoord(Direction.LEFT) })
        }
    }

    override var previousCoordTip: ICoord = Coord(listOfField.last().x, listOfField.last().y)
        private set

    override fun move() {
        previousCoordTip.x = listOfField.last().x
        previousCoordTip.y = listOfField.last().y

        for (index in listOfField.size - 1 downTo 1) {
            listOfField[index].x = listOfField[index - 1].x
            listOfField[index].y = listOfField[index - 1].y
        }

        listOfField[Constants.INDEX_HEAD_SNAKE].moveCoord(_direction)
        listOfField[Constants.INDEX_HEAD_SNAKE].also {
            moveThroughWalls(it)
        }
    }

    private fun moveThroughWalls(coord: ICoord) {
        when {
            coord.y < 0 -> {
                coord.y = map.sizeY - 1
            }
            coord.y >= map.sizeY -> {
                coord.y = 0
            }
        }
        when {
            coord.x < 0 -> {
                coord.x = map.sizeX - 1
            }
            coord.x >= map.sizeX -> {
                coord.x = 0
            }
        }
    }

    override fun grow() {
        listOfField.add(Coord(previousCoordTip.x, previousCoordTip.y))
    }

}
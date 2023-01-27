package ru.nsu.snake.game.model.gameObjects.implObjects

import ru.nsu.snake.Constants
import ru.nsu.snake.game.model.gameObjects.enumForGameObjects.ModelDirection
import ru.nsu.snake.game.model.gameObjects.interfaceObjects.ICoord
import ru.nsu.snake.game.model.gameObjects.interfaceObjects.IMapConfig
import ru.nsu.snake.game.model.gameObjects.interfaceObjects.ISnake

abstract class AbstractSnake : MapObject(), ISnake

class Snake(
    direction: ModelDirection,
    private val map: IMapConfig,
    override var listOfField: MutableList<ICoord>
) :
    AbstractSnake() {
    override var modelDirection: ModelDirection = direction
        set(value) {
            val newCoordAfterMove = listOfField[Constants.INDEX_HEAD_SNAKE].newCopy().also {
                it.moveCoord(value)
                it.moveThroughWalls(mapConfig = map)
            }
            field =
                if (newCoordAfterMove.coordEquals(listOfField[Constants.INDEX_SECOND_BLOCK_SNAKE])) {
                    modelDirection
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
            ModelDirection.UP -> listOfField.add(tipSnake.apply { moveCoord(ModelDirection.DOWN) })
            ModelDirection.DOWN -> listOfField.add(tipSnake.apply { moveCoord(ModelDirection.UP) })
            ModelDirection.LEFT -> listOfField.add(tipSnake.apply { moveCoord(ModelDirection.RIGHT) })
            ModelDirection.RIGHT -> listOfField.add(tipSnake.apply { moveCoord(ModelDirection.LEFT) })
        }
        listOfField[Constants.INDEX_SECOND_BLOCK_SNAKE].moveThroughWalls(mapConfig = map)
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

        listOfField[Constants.INDEX_HEAD_SNAKE].moveCoord(modelDirection)
        listOfField[Constants.INDEX_HEAD_SNAKE].also {
            it.moveThroughWalls(mapConfig = map)
        }
    }

    override fun grow() {
        listOfField.add(Coord(previousCoordTip.x, previousCoordTip.y))
    }

}
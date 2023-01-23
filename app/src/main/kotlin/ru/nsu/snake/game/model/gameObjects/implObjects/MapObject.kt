package ru.nsu.snake.game.model.gameObjects.implObjects

import ru.nsu.snake.Constants
import ru.nsu.snake.game.model.gameObjects.enumForGameObjects.StatusGameObj
import ru.nsu.snake.game.model.gameObjects.enumForGameObjects.TypeField
import ru.nsu.snake.game.model.gameObjects.interfaceObjects.IMapGame
import ru.nsu.snake.game.model.gameObjects.interfaceObjects.IMapObject

sealed class MapObject : IMapObject {
    override var statusGameObj: StatusGameObj = StatusGameObj.ALIVE

    override fun checkEvents(gameMap: IMapGame) {
        when (this) {
            is AbstractFood -> {
                gameMap.map[coord.y][coord.x].forEach {
                    if (it == TypeField.SNAKE) {
                        statusGameObj = StatusGameObj.DIE
                    }
                }
            }
            is AbstractSnake -> {
                gameMap.map[listOfField[Constants.INDEX_HEAD_SNAKE].y][listOfField[Constants.INDEX_HEAD_SNAKE].x].forEach {
                    when (it) {
                        TypeField.FOOD -> grow()
                        TypeField.SNAKE -> statusGameObj = StatusGameObj.DIE
                        else -> {}
                    }
                }
            }
            else -> {}
        }
    }


    override fun checkSnakeFoodEvents(gameMap: IMapGame) {
        if (this is AbstractSnake) {
            gameMap.map[listOfField[Constants.INDEX_HEAD_SNAKE].y][listOfField[Constants.INDEX_HEAD_SNAKE].x].forEach {
                if (it == TypeField.FOOD) {
                    grow()
                    gameMap.addOnMap(this.previousCoordTip as MapObject)
                }
            }
        }
    }

    override fun checkSnakeHitEvents(gameMap: IMapGame) {
        if (this is AbstractSnake) {
            var countSnakeInCell = 0
            gameMap.map[listOfField[Constants.INDEX_HEAD_SNAKE].y][listOfField[Constants.INDEX_HEAD_SNAKE].x].forEach {
                if (it == TypeField.SNAKE) {
                    countSnakeInCell += 1
                }
                if (countSnakeInCell >= 2) {
                    statusGameObj = StatusGameObj.DIE
                }
            }
        }
    }
}

package ru.nsu.snake.game.model.gameObjects.implObjects

import ru.nsu.snake.game.model.gameObjects.interfaceObjects.ICoord
import ru.nsu.snake.game.model.gameObjects.interfaceObjects.IFood

abstract class AbstractFood : MapObject(), IFood

class Food(override var coord: ICoord) : AbstractFood()
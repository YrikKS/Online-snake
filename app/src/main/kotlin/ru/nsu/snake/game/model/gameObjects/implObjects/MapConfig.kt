package ru.nsu.snake.game.model.gameObjects.implObjects

import ru.nsu.snake.game.model.gameObjects.interfaceObjects.IMapConfig

class MapConfig(
    override val sizeX: Int,
    override val sizeY: Int,
    override val gameSpeed: Int,
    override val countFood: Int
) : IMapConfig
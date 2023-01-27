package ru.nsu.snake.game.model.gameObjects.interfaceObjects

import ru.nsu.snake.game.model.gameObjects.implObjects.Coord

interface IFood : IMapObject {
    var coord: ICoord
}
package ru.nsu.snake.game.model.interfaceModel

import ru.nsu.snake.game.model.enumsForModel.ModelNodeRole
import ru.nsu.snake.game.model.enumsForModel.ModelPlayerType
import ru.nsu.snake.game.model.enumsForModel.ModelSnakeStatus
import ru.nsu.snake.game.model.gameObjects.interfaceObjects.ISnake

interface IPlayer {
    var snake: ISnake
    var snakeState: ModelSnakeStatus

    val idPlayer: Int
    val playerName: String
    var statusPLayer: ModelPlayerType?
    var ipAddres: String?
    var port: Int?
    var nodeRole: ModelNodeRole
}
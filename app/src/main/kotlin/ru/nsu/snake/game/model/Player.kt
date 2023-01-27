package ru.nsu.snake.game.model

import ru.nsu.snake.game.model.enumsForModel.ModelNodeRole
import ru.nsu.snake.game.model.enumsForModel.ModelPlayerType
import ru.nsu.snake.game.model.enumsForModel.ModelSnakeStatus
import ru.nsu.snake.game.model.gameObjects.interfaceObjects.ISnake

class Player(
    override var snake: ISnake,
    override var snakeState: ModelSnakeStatus,
    override val idPlayer: Int,
    override val playerName: String,
    override var statusPLayer: ModelPlayerType?,
    override var ipAddres: String?,
    override var port: Int?,
    override var nodeRole: ModelNodeRole
) : IPlayer {
}
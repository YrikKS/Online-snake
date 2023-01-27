package ru.nsu.snake.game.model.gameObjects.enumForGameObjects

import me.ippolitov.fit.snakes.SnakesProto

enum class ModelDirection {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    companion object {
        fun randomDirection() =
            listOf(UP, LEFT, DOWN, RIGHT).random()

        fun converFromModelToNet(modelDirection: ModelDirection): SnakesProto.Direction {
            return when (modelDirection) {
                UP -> SnakesProto.Direction.UP
                DOWN -> SnakesProto.Direction.DOWN
                LEFT -> SnakesProto.Direction.LEFT
                RIGHT -> SnakesProto.Direction.RIGHT
            }
        }

        fun convertFromNetToModel(netDirection : SnakesProto.Direction) : ModelDirection {
            return when(netDirection) {
                SnakesProto.Direction.UP -> ModelDirection.UP
                SnakesProto.Direction.DOWN -> ModelDirection.DOWN
                SnakesProto.Direction.LEFT -> ModelDirection.LEFT
                SnakesProto.Direction.RIGHT -> ModelDirection.RIGHT
            }
        }
    }
}
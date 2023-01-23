package ru.nsu.snake.game.model.enumsForModel

import me.ippolitov.fit.snakes.SnakesProto

enum class ModelPlayerType {
    HUMAN,
    ROBOT;

    companion object {
        fun convertFromModelToNet(modelNodeRole: ModelPlayerType): SnakesProto.PlayerType =
            when (modelNodeRole) {
                HUMAN -> SnakesProto.PlayerType.HUMAN
                ROBOT -> SnakesProto.PlayerType.ROBOT
            }

        fun convertFromNetToModel(netNodeRole: SnakesProto.PlayerType): ModelPlayerType =
            when (netNodeRole) {
                SnakesProto.PlayerType.HUMAN -> ModelPlayerType.HUMAN
                SnakesProto.PlayerType.ROBOT -> ModelPlayerType.ROBOT
            }
    }

}
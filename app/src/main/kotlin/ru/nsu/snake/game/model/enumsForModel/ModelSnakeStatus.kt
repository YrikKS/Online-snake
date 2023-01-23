package ru.nsu.snake.game.model.enumsForModel

import me.ippolitov.fit.snakes.SnakesProto.GameState.Snake.*

enum class ModelSnakeStatus {
    ALIVE,
    ZOMBIE;

    companion object {
        fun convertFromModelToNet(modelNodeRole: ModelSnakeStatus): SnakeState =
            when (modelNodeRole) {
                ALIVE -> SnakeState.ALIVE
                ZOMBIE -> SnakeState.ZOMBIE
            }

        fun convertFromNetToModel(netNodeRole: SnakeState): ModelSnakeStatus =
            when (netNodeRole) {
                SnakeState.ALIVE -> ModelSnakeStatus.ALIVE
                SnakeState.ZOMBIE -> ModelSnakeStatus.ZOMBIE
            }
    }
}
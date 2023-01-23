package ru.nsu.snake.game.model.gameObjects.enumForGameObjects

enum class Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    companion object {
        fun randomDirection() =
            listOf(UP, LEFT, DOWN, RIGHT).random()
    }
}
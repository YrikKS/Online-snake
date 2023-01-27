package ru.nsu.snake.game.model.gameObjects.interfaceObjects

interface IGeneratorGameObjects {
    fun generateFood(map: IMapGame): IFood?
    fun spawnNewSnake(map: IMapGame, snakes: MutableList<ISnake>): ISnake?
}
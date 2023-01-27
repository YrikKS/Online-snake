package ru.nsu.snake.game.model

import ru.nsu.snake.game.model.gameObjects.enumForGameObjects.StatusGameObj
import ru.nsu.snake.game.model.gameObjects.implObjects.GameMap
import ru.nsu.snake.game.model.gameObjects.implObjects.GeneratorGameObjects
import ru.nsu.snake.game.model.gameObjects.implObjects.MapObject
import ru.nsu.snake.game.model.gameObjects.interfaceObjects.IFood
import ru.nsu.snake.game.model.gameObjects.interfaceObjects.IMapConfig
import ru.nsu.snake.game.model.gameObjects.interfaceObjects.IMapGame

class GameMaster(
    var mapConfig: IMapConfig,
    var listUsers: MutableList<IPlayer>,
    var listFood: MutableList<IFood>
) : IGameMaster {
    private var gameMap = GameMap(mapConfig)

    override fun doGameTick() {
        gameMap.clearMap()
        listFood.forEach { gameMap.addOnMap(it) }
        listUsers.forEach {
            it.snake.move()
            gameMap.addOnMap(it.snake)
        }
        while (listFood.size < mapConfig.countFood + listUsers.size) {
            val newFood = GeneratorGameObjects.generateFood(gameMap)
            if (newFood != null) {
                listFood.add(newFood)
            } else {
                break
            }
        }
        listUsers.forEach {
            it.snake.checkSnakeFoodEvents(gameMap)
        }
        checkFoodDieEvent()
        checkSnakeDieEvent()
    }

    override fun addNewPlayer(player: IPlayer) {
        TODO("Not yet implemented")
    }

    private fun checkSnakeDieEvent() {
        val iterator = listUsers.iterator()
        while (iterator.hasNext()) {
            iterator.next().apply {
                snake.checkSnakeHitEvents(gameMap)
                if (snake.statusGameObj == StatusGameObj.DIE) {
                    iterator.remove()   //TODO: send die massage
                }
            }
        }
    }

    private fun checkFoodDieEvent() {
        val iterator = listFood.iterator()
        while (iterator.hasNext()) {
            iterator.next().apply {
                checkEvents(gameMap)
                if (statusGameObj == StatusGameObj.DIE) {
                    iterator.remove()
                }
            }
        }
    }
}
package ru.nsu.snake.game.model.gameObjects.implObjects

import ru.nsu.snake.game.model.gameObjects.enumForGameObjects.ModelDirection
import ru.nsu.snake.game.model.gameObjects.enumForGameObjects.TypeField
import ru.nsu.snake.game.model.gameObjects.interfaceObjects.*

class GeneratorGameObjects {
    companion object : IGeneratorGameObjects {
        override fun generateFood(map: IMapGame): IFood? {
            val listClearCell = mutableListOf<ICoord>()
            map.map.forEachIndexed { colIndex, col ->
                col.forEachIndexed { rowIndex, row ->
                    if (row.size == 1 && row[0] == TypeField.EMPTY) {
                        listClearCell.add(Coord(x = rowIndex, y = colIndex))
                    }
                }
            }
            if (listClearCell.isEmpty()) {
                return null
            }
            return Food(listClearCell.random().newCopy())
        }

        override fun spawnNewSnake(map: IMapGame, snakes: MutableList<ISnake>): ISnake? {
            val arrayCell = Array<Array<Boolean>>(map.mapConfig.sizeY) {
                Array(map.mapConfig.sizeX) {
                    true
                }
            }
            snakes.forEach {
                it.listOfField.forEach { snakeCell ->
                    for (i in -2..2) {
                        for (j in -2..2) {
                            arrayCell[
                                    moveThroughWalls(snakeCell.y + i, map.mapConfig.sizeY)][
                                    moveThroughWalls(snakeCell.x + j, map.mapConfig.sizeX)] =
                                false
                        }
                    }
                }
            }
//            arrayCell.forEach {
//                it.forEach {
//                    it.run(::print)
//                    print(" ")
//                }
//                println()
//            }
            val listClearCell = mutableListOf<ICoord>()
            arrayCell.forEachIndexed { col, it ->
                it.forEachIndexed { row, element ->
                    if (element) {
                        listClearCell.add(Coord(x = row, y = col))
                    }
                }
            }

            if (listClearCell.isEmpty()) {
                return null
            }
            return Snake(
                ModelDirection.randomDirection(),
                map.mapConfig,
                mutableListOf(listClearCell.random().newCopy())
            )
        }

        private fun moveThroughWalls(a: Int, mapSize: Int): Int {
            return when {
                a < 0 -> {
                    mapSize - 1
                }
                a >= mapSize -> {
                    0
                }
                else -> {
                    a
                }
            }
        }
    }


}
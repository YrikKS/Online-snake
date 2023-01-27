package ru.nsu.snake.game

import android.util.Log
import androidx.lifecycle.ViewModel
import ru.nsu.snake.Constants
import ru.nsu.snake.game.model.AdapterSender
import ru.nsu.snake.game.model.IPlayer
import ru.nsu.snake.game.model.Player
import ru.nsu.snake.game.model.enumsForModel.ModelNodeRole
import ru.nsu.snake.game.model.enumsForModel.ModelPlayerType
import ru.nsu.snake.game.model.enumsForModel.ModelSnakeStatus
import ru.nsu.snake.game.model.gameObjects.enumForGameObjects.ModelDirection
import ru.nsu.snake.game.model.gameObjects.implObjects.*
import ru.nsu.snake.game.model.gameObjects.interfaceObjects.IFood
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.net.MulticastSocket
import kotlin.concurrent.thread

class GameViewModel : ViewModel() {
    private var gameModel = GameModel()

    init {
        Log.i(Constants.logKey, "asdasdadsddddddddddddddddddddddd")
        println("ASDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDdd")
        thread {
            println("ASDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDdd")
            val adapterSender = AdapterSender()
            val mapConfig = MapConfig(
                Constants.minSizeMap,
                Constants.minSizeMap,
                Constants.minGameSpeed,
                Constants.minCountFood
            )
            val gameMap = GameMap(
                mapConfig
            )
            val players = MutableList<IPlayer>(1) {
                Player(
                    Snake(ModelDirection.RIGHT, mapConfig, mutableListOf(Coord(1, 1))),
                    ModelSnakeStatus.ALIVE,
                    1,
                    "yarik",
                    ModelPlayerType.HUMAN,
                    null,
                    9999,
                    ModelNodeRole.MASTER
                )
            }
            val foods = MutableList<IFood>(1) {
                Food(Coord(3, 1))
            }
//    runBlocking {
            try {
                println("ASDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDdd")
                println("start send")
                val byteArr = adapterSender.sendGameStateMsg(players, foods)
//                println(byteArr.size)
//        val socket = MulticastSocket(Constants.port)
                val group = InetAddress.getByName("224.0.0.1")
                val s = MulticastSocket(6789)
                s.joinGroup(group)
                val arr = ByteArray(100)
                val data1 = DatagramPacket(arr, arr.size)
                println("receve start")
                s.receive(data1)
                println("receve end")
                println(data1.data.decodeToString())
                println("AAAAAAAAAAAAAreceve end")
                println(byteArr.decodeToString())
                val data = DatagramPacket(byteArr, byteArr.size, group, 6789)
                s.send(data)
                println("end send")
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
//    }
        }
    }
}
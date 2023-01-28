package ru.nsu.snake.game

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import ru.nsu.snake.Constants
import ru.nsu.snake.argForFragments.ArgForNewGameToModel
import ru.nsu.snake.game.model.AdapterSender
import ru.nsu.snake.game.model.interfaceModel.IPlayer
import ru.nsu.snake.game.model.Player
import ru.nsu.snake.game.model.enumsForModel.ModelNodeRole
import ru.nsu.snake.game.model.enumsForModel.ModelPlayerType
import ru.nsu.snake.game.model.enumsForModel.ModelSnakeStatus
import ru.nsu.snake.game.model.gameObjects.enumForGameObjects.ModelDirection
import ru.nsu.snake.game.model.gameObjects.implObjects.*
import ru.nsu.snake.game.model.gameObjects.interfaceObjects.IFood
import java.net.DatagramPacket
import java.net.InetAddress
import java.net.MulticastSocket
import kotlin.concurrent.thread

class GameViewModel(private val arg : ArgForNewGameToModel) : ViewModel() {
    private var gameModel = GameModel()
    lateinit var params: ArgForNewGameToModel

    init {
        println(arg.gameName)
    }

    suspend fun steer(direction: ModelDirection) {
        gameModel.sendSteer(direction)
    }
}
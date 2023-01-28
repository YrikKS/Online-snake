package ru.nsu.snake.game

import ru.nsu.snake.Constants
import ru.nsu.snake.game.model.NodePlayer
import ru.nsu.snake.game.model.gameObjects.enumForGameObjects.ModelDirection
import ru.nsu.snake.network.multicastSocket.MulticastSendReceiver
import kotlin.concurrent.thread

class GameModel {

//    private val nodePlayer = NodePlayer()

    private val multicastSendReceiver = MulticastSendReceiver(Constants.IP_ADDRESS, Constants.PORT)

    var masterNode: Thread = thread {

    }

    suspend fun sendSteer(direction: ModelDirection) {

    }

    suspend fun startRecive() {
        val msg = multicastSendReceiver.receiver()
        msg.gameMessage.apply {
            when {
                hasPing() -> {}
                hasSteer() -> {}
                hasAck() -> {}
                hasState() -> {}
                hasAnnouncement() -> {}
                hasJoin() -> {}
                hasError() -> {}
                hasRoleChange() -> {}
                hasDiscover() -> {}
            }
        }
    }
}
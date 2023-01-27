package ru.nsu.snake.network

import me.ippolitov.fit.snakes.SnakesProto.GameMessage

interface ISender {
    suspend fun sendData(gameMessage: GameMessage, receiverIp : String, receiverPort: Int)
}
package ru.nsu.snake.network

import me.ippolitov.fit.snakes.SnakesProto.GameMessage

data class ReceiverData(
    val receiverIp : String,
    val receiverPort: Int,
    val gameMessage: GameMessage
)

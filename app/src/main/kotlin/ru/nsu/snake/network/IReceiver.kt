package ru.nsu.snake.network

import me.ippolitov.fit.snakes.SnakesProto

interface IReceiver {
    suspend fun receiver() : ReceiverData
}
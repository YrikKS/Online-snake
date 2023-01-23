package ru.nsu.snake.network

interface IReceiver {
    suspend fun recive() : ByteArray
}
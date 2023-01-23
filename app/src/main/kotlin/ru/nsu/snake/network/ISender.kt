package ru.nsu.snake.network

interface ISender {
    suspend fun sendData(byteArray: ByteArray)
}
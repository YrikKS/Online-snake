package ru.nsu.snake.network.multicastSocket

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.nsu.snake.Constants
import ru.nsu.snake.network.IReceiver
import ru.nsu.snake.network.ISender
import java.net.DatagramPacket

class MulticastSendReciver(address: String, port: Int) :
    UdpMulticastSock(address, port),
    ISender,
    IReceiver {
    override suspend fun sendData(byteArray: ByteArray) {
        withContext(Dispatchers.IO) {
            dataSock.send(DatagramPacket(byteArray, byteArray.size))
        }
    }

    override suspend fun recive(): ByteArray {
        val byteArray = ByteArray(Constants.sizeReceiverArray)
        withContext(Dispatchers.IO) {
            dataSock.receive(DatagramPacket(byteArray, byteArray.size))
        }
        return byteArray
    }

}
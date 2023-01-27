package ru.nsu.snake.network.multicastSocket

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.ippolitov.fit.snakes.SnakesProto
import ru.nsu.snake.Constants
import ru.nsu.snake.network.IReceiver
import ru.nsu.snake.network.ISender
import ru.nsu.snake.network.ReceiverData
import java.net.DatagramPacket
import java.net.InetAddress

class MulticastSendReciver(address: String, port: Int) :
    UdpMulticastSock(address, port),
    ISender,
    IReceiver {

    override suspend fun sendData(
        gameMessage: SnakesProto.GameMessage,
        receiverIp: String,
        receiverPort: Int
    ) {
        withContext(Dispatchers.IO) {
            var byteArray =
                SnakesProto.GameMessage.newBuilder().mergeFrom(gameMessage).build().toByteArray()
            dataSock.send(
                DatagramPacket(
                    byteArray,
                    byteArray.size,
                    InetAddress.getByName(receiverIp),
                    receiverPort
                )
            )
        }
    }

    override suspend fun receiver(): ReceiverData {
        val byteArray = ByteArray(Constants.sizeReceiverArray)
        val dataPackage = DatagramPacket(byteArray, byteArray.size)
        withContext(Dispatchers.IO) {
            dataSock.receive(dataPackage)
        }
        return ReceiverData(
            dataPackage.address.hostName, dataPackage.port,
            SnakesProto.GameMessage.parseFrom(dataPackage.data)
        )
    }
}
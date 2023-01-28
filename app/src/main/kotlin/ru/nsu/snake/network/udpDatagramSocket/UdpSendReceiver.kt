package ru.nsu.snake.network.udpDatagramSocket

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.ippolitov.fit.snakes.SnakesProto
import ru.nsu.snake.Constants
import ru.nsu.snake.network.IReceiver
import ru.nsu.snake.network.ISender
import ru.nsu.snake.network.ReceiverData
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class UdpSendReceiver(address: String, port: Int) : UdpDatagramSock(address, port), ISender,
    IReceiver {

    private var sock = DatagramSocket(Constants.PORT_PLAYER)
    override suspend fun sendData(
        gameMessage: SnakesProto.GameMessage,
        receiverIp: String,
        receiverPort: Int
    ) {
        withContext(Dispatchers.IO) {
            val byteArray =
                SnakesProto.GameMessage.newBuilder().mergeFrom(gameMessage).build().toByteArray()
            sock.send(
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
        println(dataPackage.address.hostName)
        return ReceiverData(
            dataPackage.address.hostName, dataPackage.port,
            SnakesProto.GameMessage.parseFrom(dataPackage.data)
        )
    }

}
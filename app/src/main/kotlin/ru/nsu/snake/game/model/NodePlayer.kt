package ru.nsu.snake.game.model

import ru.nsu.snake.Constants
import ru.nsu.snake.game.model.gameObjects.enumForGameObjects.ModelDirection
import ru.nsu.snake.game.model.interfaceModel.INodeMaster
import ru.nsu.snake.game.model.interfaceModel.IPlayer
import ru.nsu.snake.network.multicastSocket.UdpMulticastSock
import ru.nsu.snake.network.udpDatagramSocket.UdpSendReceiver

class NodePlayer(private val player: IPlayer, var master: INodeMaster) {
    private val sock = UdpSendReceiver(Constants.IP_ADDRESS, Constants.PORT)
    private val adapterSender = AdapterSender(player.idPlayer)

    suspend fun sendRotate(direction: ModelDirection) {
        sock.sendData(adapterSender.createRotationMsg(direction), master.ip, master.port)
    }
}
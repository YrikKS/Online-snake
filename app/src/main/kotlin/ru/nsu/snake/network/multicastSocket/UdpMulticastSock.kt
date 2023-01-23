package ru.nsu.snake.network.multicastSocket

import java.net.InetAddress
import java.net.MulticastSocket

open class UdpMulticastSock(val ipAddress: String, val port: Int) {
    protected val dataSock: MulticastSocket by lazy {
        MulticastSocket(port).apply {
            val group = InetAddress.getByName(ipAddress)
            joinGroup(group)
        }
    }
}
package ru.nsu.snake.network.udpDatagramSocket

import java.net.DatagramSocket
import java.net.InetAddress

open class UdpDatagramSock(val ipAddress : String, val port : Int) {
    protected val dataSock = DatagramSocket(port, InetAddress.getByName(ipAddress))
}
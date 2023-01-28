package ru.nsu.snake.network.multicastSocket

import java.net.InetAddress
import java.net.MulticastSocket

open class UdpMulticastSock(val ipAddress: String, val port: Int) {
    //    protected val dataSock: MulticastSocket by lazy {
//        MulticastSocket(port).apply {
//            val group = InetAddress.getByName(ipAddress)
//            println("init")
//            joinGroup(group)
//        }
//    }
    protected val dataSock = MulticastSocket(port)
    init {
        val group = InetAddress.getByName(ipAddress)
        println("init")
//        dataSock.leaveGroup(group)
        dataSock.joinGroup(group)
    }
}
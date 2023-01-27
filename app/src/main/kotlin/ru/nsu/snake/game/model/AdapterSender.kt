package ru.nsu.snake.game.model

import me.ippolitov.fit.snakes.*
import me.ippolitov.fit.snakes.GameMessageKt.ackMsg
import me.ippolitov.fit.snakes.GameMessageKt.announcementMsg
import me.ippolitov.fit.snakes.GameMessageKt.discoverMsg
import me.ippolitov.fit.snakes.GameMessageKt.pingMsg
import me.ippolitov.fit.snakes.GameMessageKt.roleChangeMsg
import me.ippolitov.fit.snakes.GameMessageKt.stateMsg
import me.ippolitov.fit.snakes.GameMessageKt.steerMsg
import me.ippolitov.fit.snakes.GameStateKt.coord
import me.ippolitov.fit.snakes.GameStateKt.snake
import ru.nsu.snake.game.model.enumsForModel.ModelNodeRole
import ru.nsu.snake.game.model.enumsForModel.ModelPlayerType
import ru.nsu.snake.game.model.enumsForModel.ModelSnakeStatus
import ru.nsu.snake.game.model.gameObjects.enumForGameObjects.ModelDirection
import ru.nsu.snake.game.model.gameObjects.interfaceObjects.IFood
import ru.nsu.snake.game.model.gameObjects.interfaceObjects.IMapConfig

class AdapterSender(private val modelSenderId: Int) {
    //    val multicastSendReciver = MulticastSendReciver(Constants.IP_ADDRESS, Constants.port)

    private var modelMsgSqn: Long = 0

    fun createToPingMsg() = gameMessage {
        ping = pingMsg {
        }
        this.msgSeq = ++modelMsgSqn
        this.senderId = modelSenderId
    }

    fun convertToGameMsg(
        modelPlayers: MutableList<IPlayer>,
        modelFoods: MutableList<IFood>
    ) = gameMessage {
        state = stateMsg {
            state = gameState {
                modelPlayers.forEach {
                    this.snakes.add(convertSnake(it))
                }
                this.players = gamePlayers {
                    modelPlayers.forEach {
                        this.players.add(convertPlayer(it))
                    }
                }
                modelFoods.forEach {
                    this.foods.add(coord {
                        x = it.coord.x
                        y = it.coord.y
                    })
                }
                this.stateOrder = 1 // TODO add state order
            }
        }
        this.senderId = modelSenderId
        this.msgSeq = ++modelMsgSqn
    }

    fun convertToRotationMsg(modelDirection: ModelDirection) = gameMessage {
        steer = steerMsg {
            this.direction = ModelDirection.converFromModelToNet(modelDirection)
        }
        this.msgSeq = ++modelMsgSqn
        this.senderId = modelSenderId
    }

    fun createAskMsg(seq: Long, modelReceiverId: Int) = gameMessage {
        ack = ackMsg {
        }
        this.msgSeq = seq
        this.receiverId = modelReceiverId
        this.senderId = modelSenderId
    }

    fun createAnnouncementMsg(gameConfig: MutableList<IGameConf>) = gameMessage {
        announcement = announcementMsg {
            gameConfig.forEach {
                games.add(gameAnnouncement {
                    this.players = gamePlayers {
                        it.listPlayers.forEach {
                            this.players.add(convertPlayer(it))
                        }
                    }
                    this.config = convertMapConfig(it.mapConfig)
                    this.gameName = it.gameName
                    if (it.canJoin != null) {
                        this.canJoin = it.canJoin!!
                    }
                })
            }
        }
        this.senderId = modelSenderId
        this.msgSeq = ++modelMsgSqn
    }

    fun createDiscoverMsg() = gameMessage {
        discover = discoverMsg {
        }
        this.senderId = modelSenderId
        this.msgSeq = ++modelMsgSqn
    }

    fun createRoleChangeMsg(
        seq: Long,
        modelReceiverId: Int,
        modelReceiverRole: ModelNodeRole?,
        modelSenderRole: ModelNodeRole?
    ) = gameMessage {
        roleChange = roleChangeMsg {
            if(modelReceiverRole != null) {
                this.receiverRole = ModelNodeRole.convertFromModelToNet(modelReceiverRole)
            }
            if(modelSenderRole != null) {
                this.senderRole = ModelNodeRole.convertFromModelToNet(modelSenderRole)
            }
        }
        this.msgSeq = seq
        this.receiverId = modelReceiverId
        this.senderId = modelSenderId
    }

    private fun convertMapConfig(mapConf: IMapConfig) = gameConfig {
        this.height = mapConf.sizeY
        this.width = mapConf.sizeX
        this.foodStatic = mapConf.countFood
        this.stateDelayMs = mapConf.gameSpeed
    }

    private fun convertSnake(modelPlayers: IPlayer) = snake {
        modelPlayers.snake.listOfField.forEach { element ->
            this.points.add(coord {
                x = element.x
                y = element.y
            })
        }
        playerId = modelPlayers.idPlayer
        state = ModelSnakeStatus.convertFromModelToNet(modelPlayers.snakeState)
        headDirection =
            ModelDirection.converFromModelToNet(modelPlayers.snake.modelDirection)
    }

    private fun convertPlayer(modelPlayers: IPlayer) = gamePlayer {
        this.name = modelPlayers.playerName
        this.id = modelPlayers.idPlayer
        this.role = ModelNodeRole.convertFromModelToNet(modelPlayers.nodeRole)
        this.score = modelPlayers.snake.listOfField.size
        if (modelPlayers.port != null) {
            this.port = modelPlayers.port!!
        }
        if (modelPlayers.ipAddres != null) {
            this.ipAddress = modelPlayers.ipAddres!!
        }
        if (modelPlayers.statusPLayer != null) {
            this.type =
                ModelPlayerType.convertFromModelToNet(modelPlayers.statusPLayer!!)
        }
    }
}

//fun main() {
//    val adapterSender = AdapterSender()
//    val mapConfig = MapConfig(
//        Constants.minSizeMap,
//        Constants.minSizeMap,
//        Constants.minGameSpeed,
//        Constants.minCountFood
//    )
//    val gameMap = GameMap(
//        mapConfig
//    )
//    val players = MutableList<IPlayer>(1) {
//        Player(
//            Snake(
//                ModelDirection.RIGHT,
//                mapConfig,
//                mutableListOf(ru.nsu.snake.game.model.gameObjects.implObjects.Coord(1, 1))
//            ),
//            ModelSnakeStatus.ALIVE,
//            1,
//            "yarik",
//            ModelPlayerType.HUMAN,
//            null,
//            9999,
//            ModelNodeRole.MASTER
//        )
//    }
//    val foods = MutableList<IFood>(1) {
//        Food(ru.nsu.snake.game.model.gameObjects.implObjects.Coord(3, 1))
//    }
////    runBlocking {
//    try {
//        println("start send")
////        val byteArr = adapterSender.sendGameStateMsg(players, foods)
//        println(byteArr.size)
////        val socket = MulticastSocket(Constants.port)
//        val socket = DatagramSocket(Constants.port, InetAddress.getByName("127.0.0.1")) //230.0.5.23
////        val group = InetAddress.getByName(Constants.IP_ADDRESS)
////        socket.joinGroup(group)
//        val data = DatagramPacket(byteArr, byteArr.size)
//        socket.send(data)
//        println("end send")
//    } catch (ex: Exception) {
//        ex.printStackTrace()
//    }
////    }
//}
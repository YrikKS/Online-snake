package ru.nsu.snake.game.model.enumsForModel

import me.ippolitov.fit.snakes.SnakesProto.NodeRole

enum class ModelNodeRole {
    NORMAL,
    MASTER,
    DEPUTY,
    VIEWER;

    companion object {
        fun convertFromModelToNet(modelNodeRole: ModelNodeRole): NodeRole = when (modelNodeRole) {
            NORMAL -> NodeRole.NORMAL
            MASTER -> NodeRole.MASTER
            DEPUTY -> NodeRole.DEPUTY
            VIEWER -> NodeRole.VIEWER
        }

        fun convertFromNetToModel(netNodeRole: NodeRole): ModelNodeRole = when (netNodeRole) {
            NodeRole.NORMAL -> ModelNodeRole.NORMAL
            NodeRole.MASTER -> ModelNodeRole.MASTER
            NodeRole.DEPUTY -> ModelNodeRole.DEPUTY
            NodeRole.VIEWER -> ModelNodeRole.VIEWER
        }
    }

}
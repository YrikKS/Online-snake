package ru.nsu.snake.argForFragments

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
class ArgForNewGameToModel(
    val width: Int,
    val height: Int,
    val countFood: Int,
    val gameName: String,
    val speedGame: Int
) : Parcelable
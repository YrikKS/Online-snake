package ru.nsu.snake.setupPlayer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.nsu.snake.R

class SetupPlayerViewModel(application: Application) : AndroidViewModel(application) {
    val collorPlayerSnake: MutableLiveData<Int> by lazy {
        MutableLiveData(application.getColor(R.color.init_player_color))
    }

    val collorEnemySnake: MutableLiveData<Int> by lazy {
        MutableLiveData(application.getColor(R.color.init_enemy_color))
    }
}
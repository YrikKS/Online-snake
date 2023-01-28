package ru.nsu.snake.newGameSettings

import android.app.Application
import androidx.lifecycle.AndroidViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.distinctUntilChanged
import ru.nsu.snake.Constants
import ru.nsu.snake.R

class NewGameSettingsViewModel(application: Application) : AndroidViewModel(application) {
    private val initMapSize = application.getString(R.string.initial_size_map).toInt()

    val mapHeight: MutableLiveData<Int> =
        MutableLiveData<Int>(initMapSize)

    val mapWidth: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>(initMapSize)
    }
    val countFood: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>(application.getString(R.string.initial_static_food).toInt())
    }
    val gameSpeed: MutableLiveData<Int> =
        MutableLiveData<Int>(application.getString(R.string.initial_speed_game).toInt())


    fun setMapHeight(newValue: String) {
        when (newValue.toIntOrNull()) {
            in Constants.minSizeMap..Constants.maxSizeMap -> mapHeight.value = newValue.toInt()
            else -> mapHeight.postValue(mapHeight.value)
        }
    }

    fun setMapWidth(newValue: String) {
        when (newValue.toIntOrNull()) {
            in Constants.minSizeMap..Constants.maxSizeMap -> mapWidth.value = newValue.toInt()
            else -> mapWidth.postValue(mapWidth.value)
        }
    }

    fun setCountFood(newValue: String) {
        when (newValue.toIntOrNull()) {
            in Constants.minCountFood..Constants.maxCountFood -> countFood.value = newValue.toInt()
            else -> countFood.postValue(countFood.value)
        }
    }

    fun setGameSpeed(newValue: String) {
        when (newValue.toIntOrNull()) {
            in Constants.minGameSpeed..Constants.maxGameSpeed -> gameSpeed.value = newValue.toInt()
            else -> gameSpeed.postValue(gameSpeed.value)
        }
    }

}
package ru.nsu.snake.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import ru.nsu.snake.argForFragments.ArgForNewGameToModel

class MyViewModelProvider(private val args: ArgForNewGameToModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GameViewModel(args) as T
    }
}
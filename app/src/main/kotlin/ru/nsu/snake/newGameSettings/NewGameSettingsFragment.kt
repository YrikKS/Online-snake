package ru.nsu.snake.newGameSettings

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.nsu.snake.Constants
import ru.nsu.snake.MainActivity
import ru.nsu.snake.R

class NewGameSettingsFragment : Fragment() {

    companion object {
        fun newInstance() = NewGameSettingsFragment()
    }

    private lateinit var viewModel: NewGameSettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_game_settings, container, false)
    }

    override fun onStart() {
        super.onStart()
        viewModel = ViewModelProvider(this)[NewGameSettingsViewModel::class.java]
    }

    override fun onResume() {
        Log.i(Constants.logKey, "settings fragment on resume")
        super.onResume()
        (activity as MainActivity).setTitle(getString(R.string.title_new_game_settings))
    }

}
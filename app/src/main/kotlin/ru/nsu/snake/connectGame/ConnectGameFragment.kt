package ru.nsu.snake.connectGame

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.nsu.snake.R

class ConnectGameFragment : Fragment() {

    companion object {
        fun newInstance() = ConnectGameFragment()
    }

    private lateinit var viewModel: ConnectGameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_connect_game, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ConnectGameViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
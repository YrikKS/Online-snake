package ru.nsu.snake.menu

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.nsu.snake.Constants
import ru.nsu.snake.MainActivity
import ru.nsu.snake.R
import ru.nsu.snake.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i(Constants.logKey, "menu fragment create")

        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        setupActionButtons()
        return binding.root
    }

    override fun onResume() {
        Log.i(Constants.logKey, "menu fragment on resume")
        super.onResume()
        (activity as MainActivity).setTitle(getString(R.string.menu_title))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupActionButtons() {
        binding.buttonNewGame.setOnClickListener {
            Log.i(Constants.logKey, "opening new game fragment")
            findNavController().navigate(R.id.action_menuFragment_to_newGameSettingsFragment)
        }

        binding.buttonConnectGame.setOnClickListener {
            Log.i(Constants.logKey, "opening connect fragment")
            findNavController().navigate(R.id.action_menuFragment_to_connectGameFragment)
        }

        binding.buttonExit.setOnClickListener {
            Log.i(Constants.logKey, "exit from main menu")
            activity?.finish()
        }
    }

}
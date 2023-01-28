package ru.nsu.snake.setupPlayer

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.nsu.snake.Constants
import ru.nsu.snake.R
import ru.nsu.snake.argForFragments.ArgForNewGameToModel
import ru.nsu.snake.databinding.FragmentNewGameSettingsBinding
import ru.nsu.snake.databinding.FragmentSetupPlayerBinding

class SetupPlayerFragment : Fragment() {
    private var _binding: FragmentSetupPlayerBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var viewModel: SetupPlayerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i(Constants.logKey, "create view player setup")
        _binding = FragmentSetupPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel = ViewModelProvider(this)[SetupPlayerViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        confButtons()
    }

    private fun confButtons() {
        binding.buttonStartGame.setOnClickListener {
            val args = SetupPlayerFragmentArgs.fromBundle(requireArguments()).settings
            val action = SetupPlayerFragmentDirections.actionSetupPlayerFragmentToGameFragment(args)
            findNavController().navigate(action)
        }
    }


}
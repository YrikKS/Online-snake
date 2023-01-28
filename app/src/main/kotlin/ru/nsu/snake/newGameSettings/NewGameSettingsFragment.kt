package ru.nsu.snake.newGameSettings

//import androidx.navigation.safe.args.generator.*
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.nsu.snake.Constants
import ru.nsu.snake.argForFragments.ArgForNewGameToModel
import ru.nsu.snake.databinding.FragmentNewGameSettingsBinding


class NewGameSettingsFragment : Fragment() {
    private lateinit var viewModel: NewGameSettingsViewModel
    private val editsTextContainer: EditsTextContainer by lazy {
        EditsTextContainer(this.viewLifecycleOwner)
    }

    private var _binding: FragmentNewGameSettingsBinding? = null
    private val binding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i(Constants.logKey, "create view game settings")
        _binding = FragmentNewGameSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        println("ok?")
        viewModel = ViewModelProvider(this)[NewGameSettingsViewModel::class.java]
        confEditText()
        confButtonNext()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun confEditText() {
        editsTextContainer.addToSet(binding.mapHeightPlain, viewModel.mapHeight) {
            viewModel.setMapHeight(it)
        }
        editsTextContainer.addToSet(binding.mapWidthPlain, viewModel.mapWidth) {
            viewModel.setMapWidth(it)
        }
        editsTextContainer.addToSet(binding.countFoodPlain, viewModel.countFood) {
            viewModel.setCountFood(it)
        }
        editsTextContainer.addToSet(binding.gameSpeedPlain, viewModel.gameSpeed) {
            viewModel.setGameSpeed(it)
        }
    }


    private fun confButtonNext() {
        binding.nextSettings.setOnClickListener {
            editsTextContainer.looseFocusAtAll()
            viewModel.gameSpeed.postValue(viewModel.gameSpeed.value)
            println("stop")

            val args = ArgForNewGameToModel(
                viewModel.mapWidth.value!!,
                viewModel.mapHeight.value!!,
                viewModel.countFood.value!!,
                "not name",
                viewModel.gameSpeed.value!!
            )
            val action =
                NewGameSettingsFragmentDirections.actionNewGameSettingsFragmentToSetupPlayerFragment(
                    args
                )
            findNavController().navigate(action)
        }
    }
}
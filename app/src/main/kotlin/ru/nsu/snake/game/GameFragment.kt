package ru.nsu.snake.game

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TableRow
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import ru.nsu.snake.Constants
import ru.nsu.snake.R
import ru.nsu.snake.databinding.FragmentGameBinding
import ru.nsu.snake.game.model.gameObjects.enumForGameObjects.ModelDirection
import ru.nsu.snake.setupPlayer.SetupPlayerFragmentArgs


class GameFragment : Fragment() {
    private var viewModel: GameViewModel? = null// by viewModels()

    private var _binding: FragmentGameBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val callback: OnBackPressedCallback =
//            object : OnBackPressedCallback(true /* enabled by default */) {
//                override fun handleOnBackPressed() {
//                    findNavController().navigate(R.id.action_gameFragment_to_menuFragment)
//                }
//            }
//        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            MyViewModelProvider(SetupPlayerFragmentArgs.fromBundle(requireArguments()).settings).create(
                GameViewModel::class.java
            )
        confButtons()
    }

    private fun confGameMap() {
        for (row in 0..Constants.countDrawableField) {
            val uri: Uri = Uri.parse("android.resource://ru.nsu.snake/${R.drawable.test_table}")
            val row = TableRow(context).apply {
                for (column in 0..Constants.countDrawableField) {
                    val imageView = ImageView(context).apply {
                        setImageURI(uri)
                        resources?.getColor(R.color.white)?.let { setColorFilter(it) }
                    }
                    addView(
                        imageView, TableRow.LayoutParams(
                            TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT,
                            0.5f
                        )
                    )
                }
            }
            binding.gameMap.addView(row)
        }
    }

    private fun confButtons() {
        binding.moveUp.setOnClickListener {
            lifecycleScope.launch {
                viewModel?.steer(ModelDirection.UP)
            }
        }
        binding.moveDown.setOnClickListener {
            lifecycleScope.launch {
                viewModel?.steer(ModelDirection.DOWN)
            }
        }
        binding.moveLeft.setOnClickListener {
            lifecycleScope.launch {
                viewModel?.steer(ModelDirection.LEFT)
            }
        }
        binding.moveRight.setOnClickListener {
            lifecycleScope.launch {
                viewModel?.steer(ModelDirection.RIGHT)
            }
        }
    }

}
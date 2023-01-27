package ru.nsu.snake.game

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TableRow
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import ru.nsu.snake.Constants
import ru.nsu.snake.R
import ru.nsu.snake.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    private var viewModel: GameViewModel? = null// by viewModels()
    
    private var _binding: FragmentGameBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[GameViewModel::class.java]
        confGameMap()
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

}
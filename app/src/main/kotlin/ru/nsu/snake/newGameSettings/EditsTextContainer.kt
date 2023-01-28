package ru.nsu.snake.newGameSettings

import android.view.KeyEvent
import android.widget.EditText
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class EditsTextContainer(private val lifecycle: LifecycleOwner) {
    var listPlainsText = HashSet<Pair<EditText, Observer<Int>>>()

    fun addToSet(value: Pair<EditText, Observer<Int>>) {
        listPlainsText.add(value)
    }

    fun addToSet(widget: EditText, observer: Observer<Int>) {
        listPlainsText.add(widget to observer)
    }

    fun addToSet(
        widget: EditText,
        liveDataElement: MutableLiveData<Int>,
        focusEvent: (String) -> Unit
    ) {
        val observer = Observer<Int> { value ->
            widget.setText(value.toString())
        }
        liveDataElement.observe(lifecycle, observer)
        widget.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                focusEvent(widget.text.toString())
            }
        }
        widget.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                focusEvent(widget.text.toString())
                return@setOnKeyListener true
            }
            false
        }
    }

    fun looseFocusAtAll() {
        listPlainsText.forEach {
            it.first.clearFocus()
        }
    }

}
package com.example.madlevel2task2

import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.ItemTouchHelper

data class Question(var question: String, var answer: Boolean) {
    companion object {
        val FALSE = ItemTouchHelper.LEFT
        val TRUE = ItemTouchHelper.RIGHT
        val QUIZ_QUESTIONS = arrayOf(
            "Mobile Application Development grants 12 ECTS F",
            "A Unit in Kotlin corresponds to a void in Java T",
            "Ajax will win the title this year T",
            "Making apps is fun T",
            "Calm music has an positive outcome on the concentration during studying T"
        )
        val QUIZ_ANSWERS = arrayOf(
            false,
            true,
            true,
            true,
            true
        )
    }
}
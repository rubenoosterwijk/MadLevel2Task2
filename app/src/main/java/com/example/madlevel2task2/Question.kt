package com.example.madlevel2task2

import androidx.annotation.DrawableRes

data class Question(var question: String) {
    companion object {
        val QUIZ_QUESTIONS = arrayOf(
            "Mobile Application Development grants 12 ECTS F",
            "A Unit in Kotlin corresponds to a void in Java T",
            "Ajax will win the title this year T",
            "Making apps is fun T",
            "Music is good during studying T"
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
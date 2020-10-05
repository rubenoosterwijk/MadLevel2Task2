package com.example.madlevel2task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }



    private fun initViews() {

        binding.rvQuestions.layoutManager =
            LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        binding.rvQuestions.adapter = questionAdapter

        //Populate the quesions list ...
        for (i in Question.QUIZ_QUESTIONS.indices) {
            questions.add(Question(Question.QUIZ_QUESTIONS[i]))
        }

        binding.rvQuestions.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
        )
        createItemTouchHelper().attachToRecyclerView(rvQuestions)
        questionAdapter.notifyDataSetChanged()
    }


    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback =  object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                var position = viewHolder.adapterPosition

                if (direction == ItemTouchHelper.LEFT) {
                    checkAnswer(false, position)
                }
                if (direction == ItemTouchHelper.RIGHT) {
                    checkAnswer(true, position)
                }
            }
        }
        return ItemTouchHelper((callback))
    }

    private fun checkAnswer(answer: Boolean, position: Int) {
        if (Question.QUIZ_ANSWERS[position] == answer){
            questions.removeAt(position)
            questionAdapter.notifyDataSetChanged()
        } else
            Toast.makeText(this, "Think Again!", Toast.LENGTH_SHORT).show()
    }

}


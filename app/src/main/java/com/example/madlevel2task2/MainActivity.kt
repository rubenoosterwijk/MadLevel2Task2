package com.example.madlevel2task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.Question.Companion.FALSE
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
        binding.rvQuestions.layoutManager =LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        binding.rvQuestions.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        binding.rvQuestions.adapter = questionAdapter
        createItemTouchHelper().attachToRecyclerView(rvQuestions)
        populateQuestions()
    }


    //Populate the quesions list ...
    private fun populateQuestions() {
        for (i in Question.QUIZ_QUESTIONS.indices) {
            questions.add(Question(Question.QUIZ_QUESTIONS[i], Question.QUIZ_ANSWERS[i]))
        }
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
                fun checkDirection(): Boolean{
                    return direction != FALSE
                }

                var position = viewHolder.adapterPosition
                if(questions[position].answer == checkDirection()) {
                    questions.removeAt(position)
                    Snackbar.make(rvQuestions,"Noice!", Snackbar.LENGTH_SHORT).show()
                } else {
                    Snackbar.make(rvQuestions,"That is incorrect, try again.", Snackbar.LENGTH_SHORT).show()
                }
                questionAdapter.notifyDataSetChanged()

                if(questions.size == 0) {
                    Snackbar.make(rvQuestions,"Very Noice!!, go again maybe?", Snackbar.LENGTH_SHORT).show()
                    populateQuestions()

                }
            }
        }
        return ItemTouchHelper(callback)
    }
}


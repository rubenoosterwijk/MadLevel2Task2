package com.example.madlevel2task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.ItemQuestionBinding

//Create a class called PlaceAdapter
class QuestionAdapter (private val questions: List<Question>):
    RecyclerView.Adapter<QuestionAdapter.ViewHolder>(){


    //Add an inner class ViewHolder
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        //In the ViewHolder bind the image and name to the ImageView
        //and TextView using kotlin synthetics
        val binding = ItemQuestionBinding.bind(itemView)
        fun databind(question: Question){
            binding.tvPlace.text = question.question
        }
    }

    //Let the PlaceAdapter extend RecyclerView.ViewHolder and implement the methods.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind((questions[position]))
    }

}
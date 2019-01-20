package com.eric.karschner.pcbuilder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.multiple_answer_list_item.view.*

class MultipleAnswerAdapter(val answers: ArrayList<String>, val context: Context) : RecyclerView.Adapter<MultipleAnswerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MultipleAnswerAdapter.ViewHolder{

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.multiple_answer_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.answerTV.text = answers[position]

        if (answers[position] in uses){
            holder.answer_cb.isChecked = true
        }

    }

    override fun getItemCount() = answers.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val item = view
        val answer_cb = view.answer_check_box
        val answerTV = view.multiple_answer_tv

        init{

            if (index == 0) {

                answer_cb.setOnClickListener {
                    if (answers[adapterPosition] in uses){
                        Log.i("Removing", answers[adapterPosition])
                        uses.remove(answers[adapterPosition])
                    } else {
                        Log.i("Adding", answers[adapterPosition])
                        uses.add(answers[adapterPosition])
                    }
                }
                item.setOnClickListener {
                    answer_cb.callOnClick()
                    answer_cb.toggle()
                }
            }
        }
    }
}
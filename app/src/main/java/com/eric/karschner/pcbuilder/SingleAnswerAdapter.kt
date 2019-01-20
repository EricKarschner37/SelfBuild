package com.eric.karschner.pcbuilder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import kotlinx.android.synthetic.main.single_answer_list_item.view.*


class SingleAnswerAdapter(val answers: ArrayList<String>, val context: Context) : RecyclerView.Adapter<SingleAnswerAdapter.ViewHolder>() {

    var checkedButton : RadioButton? = null

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): SingleAnswerAdapter.ViewHolder{
        Log.i("Answers", answers.toString())

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.single_answer_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i("Answers", answers.toString())

        holder.answerTV.text = answers[position]

        when(index){
            1 ->{
                if (answers[position] == mainUse){
                    holder.answer_rb.isChecked = true
                    checkedButton = holder.answer_rb
                }
            }

            2 ->{
                if (answers[position] == storage){
                    holder.answer_rb.isChecked = true
                    checkedButton = holder.answer_rb
                }
            }
        }

    }

    override fun getItemCount() = answers.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val item = view
        val answer_rb = view.answer_radio_button
        val answerTV = view.single_answer_tv

        init{


            item.setOnClickListener {
                if (checkedButton == null){
                    answer_rb.isChecked = true
                    checkedButton = answer_rb
                } else if (checkedButton == answer_rb) {
                    answer_rb.isChecked = false
                    checkedButton = null
                } else {
                    checkedButton?.isChecked = false
                    checkedButton = answer_rb
                    answer_rb.isChecked = true
                }

                if (index == 1){
                    Log.i("Adapter ischecked", answer_rb.isChecked.toString())
                    mainUse = if (!answer_rb.isChecked) null else answers[adapterPosition]
                } else if (index == 2){
                    storage = if (!answer_rb.isChecked) null else answers[adapterPosition]
                }
            }
            answer_rb.setOnClickListener {
                if (checkedButton == null){
                    checkedButton = answer_rb
                } else if (checkedButton == answer_rb) {
                    checkedButton?.isChecked = false
                    checkedButton = null
                } else {
                    checkedButton?.isChecked = false
                    checkedButton = answer_rb
                }

                if (index == 1){
                    Log.i("Adapter ischecked", answer_rb.isChecked.toString())
                    mainUse = if (!answer_rb.isChecked) null else answers[adapterPosition]
                } else if (index == 2){
                    storage = if (!answer_rb.isChecked) null else answers[adapterPosition]
                }
            }
        }
    }
}
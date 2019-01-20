package com.eric.karschner.pcbuilder

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_question.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton

val uses : ArrayList<String> = arrayListOf()
var mainUse : String? = null
var storage : String? = null
var budget : Int = 1000

class QuestionActivity : AppCompatActivity() {

    lateinit var slide_out_left: Animation
    lateinit var slide_out_right: Animation
    lateinit var slide_in_left: Animation
    lateinit var slide_in_right: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)


        slide_in_left = AnimationUtils.loadAnimation(this, R.anim.slide_in_left)
        slide_in_right = AnimationUtils.loadAnimation(this, R.anim.slide_in_right)

        slide_out_right = AnimationUtils.loadAnimation(this, R.anim.slide_out_right)
        slide_out_left = AnimationUtils.loadAnimation(this, R.anim.slide_out_left)

        slide_out_right.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationEnd(animation: Animation?) {
                openQuestion(questions[index])
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationStart(animation: Animation?) {

            }
        })
        slide_out_left.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationEnd(animation: Animation?) {
                openQuestion(questions[index])
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationStart(animation: Animation?) {

            }
        })

        val seekBarListener = object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                price_tv.text = "$${(450 + 1050 * progress / 100)}"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                budget = (450 + 1050*price_seek_bar.progress/100)
            }
        }
        price_seek_bar.setOnSeekBarChangeListener(seekBarListener)
        answer_rv.layoutManager = LinearLayoutManager(this)

        openQuestion(questions[index])
    }

    fun onNextButton(view: View){
        when (index){
            0 -> nextFromIndex0()
            1 -> nextFromIndex1()
            2 -> nextFromIndex2()
            3 -> openPCView()
        }
    }

    fun nextFromIndex0(){
        Log.i("Uses", uses.toString())

        if (uses == arrayListOf<String>()) {
            noSelectionAlert()
            return
        } else if (uses.size == 1) {
            mainUse = uses[0]
        }
        index ++
        populateUsesLayout()

        root.startAnimation(slide_out_left)
    }

    fun nextFromIndex1(){

        if (mainUse.isNullOrBlank()) {
            noSelectionAlert()
            return
        }
        index ++

        root.startAnimation(slide_out_left)
    }

    fun nextFromIndex2(){

        if (storage.isNullOrBlank()) {
            noSelectionAlert()
            return
        }
        index ++

        root.startAnimation(slide_out_left)
    }

    fun openPCView(){

        index = 0
        val intent = Intent(this, PCActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun openQuestion(question: Question){

        Log.i("Question", question.toString())
        question_tv.text = question.question
        prepareLayout(question)
    }

    fun onBackButton(view: View){
        onBackPressed()
    }


    fun prepareLayout(question: Question){

        when(question.answerType){
            "multiple" ->  prepareMultipleAnswerLayout(question)

            "single" ->  prepareSingleAnswerLayout(question)

            "slider" -> prepareSliderAnswerLayout(question)
            else -> {
                Log.e("AnswerType", question.answerType)
            }
        }
    }

    fun noSelectionAlert(){
        alert(getString(R.string.make_selection)) {
            okButton {  }
        }.show()
    }

    fun populateUsesLayout(){

        if (uses.size < 2){
            index ++
        } else {
            questions[index].answers.removeAll(questions[index].answers)
            questions[index].answers.addAll(uses)
        }
    }



    fun prepareMultipleAnswerLayout(question: Question){
        if (index == 1) {
            questions[1].answers.removeAll(questions[1].answers)
            uses.removeAll(uses)
        }

        price_seek_bar.visibility = View.GONE
        prices_layout.visibility = View.GONE
        answer_rv.visibility = View.VISIBLE
        as_many_tv.visibility = View.VISIBLE
        val adapter = MultipleAnswerAdapter(question.answers, this)
        answer_rv.adapter = adapter
    }

    fun prepareSingleAnswerLayout(question: Question){
        Log.i("Question", question.toString())
        price_seek_bar.visibility = View.GONE
        prices_layout.visibility = View.GONE
        as_many_tv.visibility = View.GONE
        answer_rv.visibility = View.VISIBLE
        val adapter = SingleAnswerAdapter(question.answers, this)
        answer_rv.adapter = adapter
    }

    fun prepareSliderAnswerLayout(question: Question){
        as_many_tv.visibility = View.GONE
        answer_rv.visibility = View.GONE
        prices_layout.visibility = View.VISIBLE
        price_seek_bar.visibility = View.VISIBLE
    }


    override fun onBackPressed() {

        Log.i("onBackPressed", index.toString())
        root.startAnimation(slide_out_right)
        when (index){
            0 -> super.onBackPressed()
            1 -> {
                index = 0
            }
            2 -> {

                if (uses.size < 2) {
                    index = 0
                } else {
                    index = 1
                    populateUsesLayout()
                }

            }
            else -> {
                index -= 1
            }
        }
    }
}
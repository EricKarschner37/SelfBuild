package com.eric.karschner.pcbuilder

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert

val questions = ArrayList<Question>()
var index = 0
const val PREFERENCES = "prefs"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        makeQuestions()

        val sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        val pcIndex = sharedPreferences.getInt("PCIndex", -1)

        checkPCProgress(pcIndex)

    }

    fun checkPCProgress(pcIndex: Int){
        if (pcIndex != -1) { //User already has a PC build
            resume_button.visibility = View.VISIBLE
            start_button.text = getString(R.string.start_over)
            start_button.setOnClickListener { startOver(it) }
            welcome_tv.text = getString(R.string.already_done)
        }
    }

    fun openPCScreen(view: View){
        val intent = Intent(this, PCActivity::class.java)
        startActivity(intent)
    }

    fun startOver(view: View){

        alert(getString(R.string.discard_confirm)){
            negativeButton(getString(R.string.no)){}
            positiveButton(getString(R.string.yes)){
                val sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
                sharedPreferences.edit().putInt("PCIndex", -1).apply()
                val intent = Intent(this@MainActivity, QuestionActivity::class.java)
                startActivity(intent)
            }
        }.show()
    }

    fun startQuestions(view: View){
        val intent = Intent(this@MainActivity, QuestionActivity::class.java)
        startActivity(intent)
    }

    fun makeQuestions(){

        questions.add(Question(
            getString(R.string.uses_question),
            arrayListOf(getString(R.string.gaming), getString(R.string.office_work),
                getString(R.string.video_editing), getString(R.string.web_browsing)),
            "multiple"
        ))

        questions.add(Question(
            getString(R.string.primary_use_question),
            arrayListOf(),
            "single"
        ))

        questions.add(Question(
            getString(R.string.storage_question),
            arrayListOf("1000+ GB", "240 GB", "160 GB", getString(R.string.unknown)),
            "single"
        ))

        questions.add(Question(
            getString(R.string.budget_question),
            arrayListOf(),
            "slider"
        ))
    }
}

//answerType can be single, multiple, or slider
data class Question(val question: String, val answers: ArrayList<String>, val answerType: String)
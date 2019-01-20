package com.eric.karschner.pcbuilder

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_step.*
import org.jetbrains.anko.alert

class StepActivity : AppCompatActivity() {
    val steps = ArrayList<Step>()
    var index = 0

    lateinit var  slide_out_right : Animation
    lateinit var slide_out_left : Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step)

        slide_out_right = AnimationUtils.loadAnimation(this, R.anim.slide_out_right)
        slide_out_left = AnimationUtils.loadAnimation(this, R.anim.slide_out_left)

        slide_out_left.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationEnd(animation: Animation?) {
                loadStep()
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationStart(animation: Animation?) {

            }
        })
        slide_out_right.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationEnd(animation: Animation?) {
                loadStep()
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationStart(animation: Animation?) {

            }
        })

        addSteps()

        loadStep()
    }

    fun loadStep(){
        when(index){
            0 -> {
                back_step_button.text = getString(R.string.view_parts)
                back_step_button.setOnClickListener{backToParts(it)}
            }

            1 -> {
                back_step_button.text = getString(R.string.back)
                back_step_button.setOnClickListener{onBackButton(it)}
            }

            steps.size - 2 -> {
                next_step_button.text = getString(R.string.next)
                next_step_button.setOnClickListener{onNextButton(it)}
            }

            steps.size - 1 -> {
                next_step_button.text = getString(R.string.start_over)
                next_step_button.setOnClickListener{startOver()}
            }
        }

        val step = steps[index]
        step_title_tv.text = step.title
        step_desc_tv.text = step.desc
        step_steps_tv.text = step.steps
    }

    fun onNextButton(view: View){
        //steps_root.startAnimation(slide_out_left)
        index++
        loadStep()
    }

    fun onBackButton(view: View){
        //steps_root.startAnimation(slide_out_right)
        index--
        loadStep()
    }

    fun startOver(){
        alert(getString(R.string.discard_confirm)){
            negativeButton(getString(R.string.no)){}
            positiveButton(getString(R.string.yes)){
                val intent = Intent(this@StepActivity, QuestionActivity::class.java)
                startActivity(intent)
                val sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
                sharedPreferences.edit().putInt("PCIndex", -1).apply()
            }
        }.show()
    }

    fun backToParts(view: View){
        val intent = Intent(this@StepActivity, PCActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        when(index){
            0 -> super.onBackPressed()
            else -> onBackButton(View(this))
        }
    }

    fun addSteps(){

        val psuStep = Step(getString(R.string.psu_title), getString(R.string.psu_desc), getString(R.string.psu_steps))
        val cpuStep = Step(getString(R.string.cpu_title), getString(R.string.cpu_desc), getString(R.string.cpu_steps))
        val ramStep = Step(getString(R.string.ram_title), getString(R.string.ram_desc), getString(R.string.ram_steps))
        val mbStep = Step(getString(R.string.mb_title), getString(R.string.mb_desc), getString(R.string.mb_steps))
        val storageStep = Step(getString(R.string.storage_title), getString(R.string.storage_desc), getString(R.string.storage_steps))
        val plugStep = Step(getString(R.string.plug_title), getString(R.string.plug_desc), getString(R.string.plug_steps))
        val gpuStep = Step(getString(R.string.gpu_title), getString(R.string.gpu_desc), getString(R.string.gpu_steps))
        val doneStep = Step(getString(R.string.done_title), getString(R.string.done_desc), getString(R.string.done_steps))

        steps.add(psuStep)
        steps.add(cpuStep)
        steps.add(ramStep)
        steps.add(mbStep)
        steps.add(storageStep)
        steps.add(plugStep)
        if (pc.gpu != null) {
            steps.add(gpuStep)
        }
        steps.add(doneStep)
    }
}

data class Step(val title: String, val desc: String, val steps: String)
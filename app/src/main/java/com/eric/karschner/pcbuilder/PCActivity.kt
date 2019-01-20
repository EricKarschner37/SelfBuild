package com.eric.karschner.pcbuilder

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_pc.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton

lateinit var pc: PCOption

class PCActivity : AppCompatActivity() {
    val parts = ArrayList<PCPart>()
    var budgetString = when(budget){
        in 450..800 -> "Low"
        in 800..1200 -> "Medium"
        in 1200..1500 -> "High"
        else -> "Medium"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pc)

        val sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        val pcIndex = sharedPreferences.getInt("PCIndex", -1)
        pc = if (pcIndex == -1) choosePC() else PCOptions[pcIndex]

        Log.i("PCIndex", pcIndex.toString())
        Log.i("pc", pc.toString())
        addParts(pc)

        val prefsEditor = sharedPreferences.edit()
        prefsEditor.putInt("PCIndex", PCOptions.indexOf(pc)).apply()

        parts_rv.layoutManager = LinearLayoutManager(this)
        parts_rv.adapter = PartAdapter(parts, this)
    }

    fun startBuilding(view: View){
        val intent = Intent(this, StepActivity::class.java)
        startActivity(intent)
    }

    fun startOver(view: View){
        alert(getString(R.string.discard_confirm)){
            negativeButton(getString(R.string.no)){}
            positiveButton(getString(R.string.yes)){
                val intent = Intent(this@PCActivity, QuestionActivity::class.java)
                startActivity(intent)
                val sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
                sharedPreferences.edit().putInt("PCIndex", -1).apply()
            }
        }.show()
    }

    fun choosePC(): PCOption{

        val pc = when(mainUse){
            "Gaming" -> {
                if (budgetString == "Low"){
                    gamingLowBudget

                } else if(budgetString == "Medium"){
                    gamingMediumBudget

                } else {
                    gamingHighBudget
                }
            }
            "Office Work" -> {
                if (storage.equals("1000+ GB")) officeHighStorage else officeMediumStorage
            }
            "Video Editing" -> {
                if (storage.equals("1000+ GB")) videoEditingHighStorage else videoEditingMediumStorage
            }
            else -> {
                officeHighStorage
            }
        }
        addParts(pc)
        return checkPrice(pc)
    }

    fun addParts(pc: PCOption){

        parts.removeAll(parts)

        parts.add(pc.cpu)
        if (pc.gpu != null) {
            parts.add(pc.gpu)
        }
        parts.add(pc.motherboard)
        parts.add(pc.ram)
        parts.add(pc.storage)
        parts.add(pc.psu)
        parts.add(pc.case)
    }

    fun checkPrice(pc: PCOption): PCOption{
        val price = parts.sumBy { it.price }
        Log.i("Budget", budget.toString())

        return if (price > budget){
            when(budgetString){
                "High" -> {
                    budgetString = "Medium"
                    choosePC()
                }
                "Medium" -> {
                    budgetString = "Low"
                    choosePC()
                }
                "Low" -> {
                    alert(getString(R.string.no_computer)){
                        okButton {  }
                    }.show()
                    pc
                }
                else -> pc
            }
        } else pc
    }
}

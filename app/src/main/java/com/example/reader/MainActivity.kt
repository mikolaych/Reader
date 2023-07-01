package com.example.reader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.view.View
import android.widget.Chronometer
import android.widget.Toast
import com.example.reader.databinding.ActivityMainBinding
import kotlinx.coroutines.NonCancellable.start
import kotlin.system.measureTimeMillis

var mainList = listOf<String>()
var endArray = mutableListOf<String>()
var tempArray = listOf<String>()
var wordNum = 0
var stTime : Long = 0

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var timer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        inputText()
        ready()
        stop()




    }



    private fun inputText() {
        binding.btnInput.setOnClickListener {
            if (binding.editText.text.isNullOrBlank()) {
                binding.info.visibility = View.VISIBLE
                binding.info.text = "Введите текст!"
            } else {
                binding.btnReady.visibility = View.VISIBLE
                binding.btnInput.visibility = View.INVISIBLE
                binding.editText.visibility = View.INVISIBLE
                binding.timer.visibility = View.VISIBLE
                var inText = binding.editText.text.toString()
                val delimeter = " "
                mainList = inText.split(delimeter)

            }
        }
    }

    private fun ready() {
        binding.btnReady.setOnClickListener {
            if (wordNum< mainList.size){
            timeToReady()
            visibilityOn()
                wordNum++
            } else end()
        }
    }


    private fun timeToReady() {
        timer?.cancel()
        timer = object : CountDownTimer(3000,1000){
            override fun onTick(p0: Long) {
                binding.timer.text = (p0/1000).toString()
            }
            override fun onFinish() {
                binding.viewText.text = mainList[wordNum]
                binding.btnReady.visibility = View.INVISIBLE
                binding.btnStop.visibility = View.VISIBLE
                timeToStop()
            }
        }.start()
    }

    private fun timeToStop() {
        val startTime: Long = 10000
        timer?.cancel()
        timer = object : CountDownTimer(startTime,1000){
            override fun onTick(p0: Long) {
                binding.timer.text = (startTime/1000 - (p0/1000)).toString()
            }
            override fun onFinish() {
                binding.viewText.text = mainList[wordNum]
                binding.btnReady.visibility = View.INVISIBLE
                binding.btnStop.visibility = View.VISIBLE
            }
        }.start()
    }



    private fun stop() {
        binding.btnStop.setOnClickListener {
            timer?.cancel()
            binding.viewText.visibility = View.INVISIBLE
            binding.shText.visibility = View.VISIBLE
            binding.btnStop.visibility = View.INVISIBLE
            binding.btnReady.visibility = View.VISIBLE
            endArray.add((wordNum - 1), binding.shText.text.toString())
        }
    }

    private fun end() {

    }


    private fun visibilityOn() {
        binding.editText.visibility = View.INVISIBLE
        binding.btnInput.visibility = View.INVISIBLE
        binding.viewText.visibility = View.VISIBLE
        binding.shText.visibility = View.VISIBLE

        binding.headFalse.visibility = View.VISIBLE
        binding.headTrue.visibility = View.VISIBLE
        binding.trueWin.visibility = View.VISIBLE
        binding.falseWin.visibility = View.VISIBLE
        binding.headGrade.visibility = View.VISIBLE
        binding.grade.visibility = View.VISIBLE
        binding.info.text = null

    }

    private fun visibilityOff() {
        binding.btnInput.visibility = View.VISIBLE
        binding.editText.visibility = View.VISIBLE
        binding.viewText.visibility = View.INVISIBLE
        binding.shText.visibility = View.INVISIBLE
        binding.timer.visibility = View.INVISIBLE
        binding.headFalse.visibility = View.INVISIBLE
        binding.headTrue.visibility = View.INVISIBLE
        binding.trueWin.visibility = View.INVISIBLE
        binding.falseWin.visibility = View.INVISIBLE
        binding.headGrade.visibility = View.INVISIBLE
        binding.grade.visibility = View.INVISIBLE
        binding.info.text = null

    }
}
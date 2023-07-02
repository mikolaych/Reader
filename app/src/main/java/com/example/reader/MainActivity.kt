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
var exampleArray = mutableListOf<String>()
var tempArray = listOf<String>()
lateinit var tempWord :String
lateinit var tempWordEtal :String
var wordNum = 0
var plusTime : Long = 0
var trueNum = 0
var falseNum = 0


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var timer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        inputText()

    }



    private fun inputText() {
        binding.btnInput.setOnClickListener {
            if (binding.editText.text.isNullOrBlank()) {
                binding.info.visibility = View.VISIBLE
                binding.info.text = "Введите текст!"
            } else {
                ready()
                binding.btnInput.visibility = View.INVISIBLE
                binding.editText.visibility = View.INVISIBLE

                var inText = binding.editText.text.toString()
                val delimeter = " "
                mainList = inText.split(delimeter)

                binding.info.text = null

            }
        }
    }

    private fun ready() {
        binding.shText.visibility = View.INVISIBLE

        binding.btnReady.visibility = View.VISIBLE
        binding.timer.visibility = View.VISIBLE


        binding.btnReady.setOnClickListener {
            binding.apply {
                btnReady.visibility = View.INVISIBLE

                shText.visibility = View.VISIBLE
                headFalse.visibility = View.VISIBLE
                headTrue.visibility = View.VISIBLE
                trueWin.visibility = View.VISIBLE
                falseWin.visibility = View.VISIBLE
                headNumWords.visibility = View.VISIBLE
                numWords.visibility = View.VISIBLE

            }
            timeToReady()
        }
    }


    private fun timeToReady() {
        timer?.cancel()
        timer = object : CountDownTimer(3000,1000){
            override fun onTick(p0: Long) {
                binding.timer.text = (p0/1000).toString()
            }
            override fun onFinish() {
                binding.btnStop.visibility = View.VISIBLE
                binding.viewText.visibility = View.VISIBLE

                binding.viewText.text = mainList[wordNum]
                wordNum++

                timeToStop()
                stop()
            }
        }.start()
    }

    private fun timeToStop() {
        val startTime: Long = 10000
        timer?.cancel()
        timer = object : CountDownTimer(startTime,1000){
            override fun onTick(p0: Long) {
                binding.timer.text = (startTime/1000 - (p0/1000)).toString()
                binding.timerSum.text = (startTime - p0).toString()
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
            binding.viewText.visibility = View.INVISIBLE
            binding.btnStop.visibility = View.INVISIBLE

            tempWordEtal = binding.viewText.text.toString()


            timer?.cancel()
            plusTime += binding.timerSum.text.toString().toLong()

            binding.timer.text = null

            inputWord()
        }
    }

    private fun inputWord() {
        binding.btnNext.visibility = View.VISIBLE
        binding.shText.visibility = View.VISIBLE

        binding.btnNext.setOnClickListener {
            if (binding.shText.text.isNullOrEmpty()){
                binding.info.text = "Введите слово!"
            } else {
                exampleArray.add((wordNum - 1), binding.shText.text.toString())
                tempWord = binding.shText.text.toString()
                if (binding.shText.text.toString() == tempWordEtal) {
                    trueNum++
                    binding.trueWin.text = trueNum.toString()
                } else {
                    falseNum++
                    binding.falseWin.text = falseNum.toString()
                }
                binding.btnNext.visibility = View.INVISIBLE
                binding.info.text = null
                binding.shText.text = null
                binding.viewText.text = null

                var numberWordsMin = wordNum * plusTime / 1000
                binding.numWords.text = numberWordsMin.toString()
                timeToReady()

            }


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
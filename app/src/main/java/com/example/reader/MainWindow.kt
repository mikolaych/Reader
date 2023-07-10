package com.example.reader

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.reader.databinding.MainWindowBinding

var mainList = listOf<String>()
var exampleArray = mutableListOf<String>()
lateinit var tempWord :String
lateinit var tempWordEtal :String
var wordNum = 0
var plusTime : Long = 0
var trueNum = 0
var falseNum = 0

//Входящие данные
var checkTime: Boolean = false
var startTime: Long = 5000
var timeplus: Long = 2000
var numLvl: Int = 1
var numExample: Int = 1
var words: Int = 0


class MainWindow : Fragment() {

    lateinit var binding: MainWindowBinding
    private var timer: CountDownTimer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainWindowBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


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
                num.visibility = View.VISIBLE
                slash.visibility = View.VISIBLE
                size.visibility = View.VISIBLE


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
                binding.num.text = wordNum.toString()
                binding.size.text = mainList.size.toString()

                timeToStop(startTime)
                stop()

            }
        }.start()
    }

    private fun timeToStop(startTime: Long) {
        timer?.cancel()
        timer = object : CountDownTimer(startTime,1){
            override fun onTick(p0: Long) {

                binding.timer.text = (startTime - p0).toString()


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


            inputWord()
        }

    }

    private fun inputWord() {



        binding.btnNext.visibility = View.VISIBLE
        binding.shText.visibility = View.VISIBLE

        binding.btnNext.setOnClickListener {


            if (binding.shText.text.isNullOrEmpty()) {
                binding.info.text = "Введите слово!"
            } else {
                exampleArray.add((wordNum - 1), binding.shText.text.toString())
                tempWord = binding.shText.text.toString()
                if (binding.shText.text.toString() == tempWordEtal) {
                    trueNum++
                    plusTime += binding.timer.text.toString().toLong()
                    binding.numWords.text = (trueNum * 60000 / plusTime).toString()
                    binding.trueWin.text = trueNum.toString()
                } else {
                    falseNum++
                    binding.falseWin.text = falseNum.toString()
                }



                binding.btnNext.visibility = View.INVISIBLE
                binding.info.text = null
                binding.timer.text = null
                binding.shText.text = null
                binding.viewText.text = null
                if (wordNum < mainList.size) {
                    timeToReady()
                } else end()
            }
        }


    }

    private fun end() {
        binding.apply {
            editText.visibility = View.VISIBLE
            headGrade.visibility = View.VISIBLE
            grade.visibility = View.VISIBLE
            info.visibility = View.VISIBLE



            shText.visibility = View.INVISIBLE
            timer.visibility = View.INVISIBLE
            headFalse.visibility = View.INVISIBLE
            headTrue.visibility = View.INVISIBLE
            trueWin.visibility = View.INVISIBLE
            falseWin.visibility = View.INVISIBLE
            num.visibility = View.INVISIBLE
            slash.visibility = View.INVISIBLE
            size.visibility = View.INVISIBLE

        }

        binding.info.text = exampleArray.toString()
        binding.grade.text = falseNum.toString()



    }


}
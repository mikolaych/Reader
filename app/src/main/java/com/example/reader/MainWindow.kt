package com.example.reader

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.example.reader.databinding.MainWindowBinding

var mainList = listOf<String>()
var exampleArray = mutableListOf<String>()
lateinit var tempWord :String
lateinit var tempWordEtal :String
var wordNum = 0
var plusTime : Long = 0
var trueNum = 0
var falseNum = 0
var level = 3

//Входящие данные
var checkTime: Boolean = false
var startTime: Long = 5000
var timeplus: Long = 2000
var numLvl: Int = 0
var numExample: Int = 0
var words: Int = 0


class MainWindow : Fragment() {

    lateinit var binding: MainWindowBinding
    private val openModel: OpenModel by activityViewModels()
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

//LifeData
        openModel.numExercise.observe(activity as LifecycleOwner) {
            numExample = it
        }
        openModel.numLvl.observe(activity as LifecycleOwner) {
            numLvl = it
        }
        openModel.timer.observe(activity as LifecycleOwner) {
            startTime = it
        }
        openModel.timerPlus.observe(activity as LifecycleOwner) {
            timeplus = it
        }
        openModel.words.observe(activity as LifecycleOwner) {
            words = it
        }
        openModel.checkTimer.observe(activity as LifecycleOwner) {
            checkTime = it
        }


        inputText()
    }
//Ввод текста
    private fun inputText() {
        binding.btnInput.setOnClickListener {
            if (binding.editText.text.isNullOrBlank()) {
                binding.info.visibility = View.VISIBLE
                binding.info.text = "Введите текст!"
            } else {

                var inText = binding.editText.text.toString()
                val delimeter = " "
                mainList = inText.split(delimeter)
                if (mainList.size < words){
                    binding.info.text = "Слов должно быть не менее $words!"
                } else {

                binding.btnInput.visibility = View.INVISIBLE
                binding.editText.visibility = View.INVISIBLE

                binding.info.text = null

                ready()
                }

            }
        }
    }



//Готовность UI

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
            timeToReady(level)

        }
    }



    //Обратный отсчет, вывод слов на экран из списка
    private fun timeToReady(level: Int) {
        if (level <= numLvl){   //Контроль уровня
            timer?.cancel()
            timer = object : CountDownTimer(3000,1000){
                override fun onTick(p0: Long) {
                    binding.timer.text = (p0/1000).toString()
                }
                override fun onFinish() {

                    binding.btnStop.visibility = View.VISIBLE
                    binding.viewText.visibility = View.VISIBLE

                    when (level) {
                        1 -> {
                            binding.viewText.text = mainList[wordNum]
                            binding.num.text = wordNum.toString()
                            binding.size.text = mainList.size.toString()
                            wordNum++
                        }

                        2 -> {
                            binding.viewText.text = mainList[wordNum] + " " + mainList[wordNum + 1]
                            wordNum += 2
                        }

                        3 -> {
                            binding.viewText.text =
                                mainList[wordNum] + " " + mainList[wordNum + 1] + " " + mainList[wordNum + 2]
                            wordNum += 3
                        }

                        4 -> {
                            binding.viewText.text =
                                mainList[wordNum] + " " + mainList[wordNum + 1] + " "  + mainList[wordNum + 2] + " " + mainList[wordNum + 3]
                            wordNum += 4
                        }
                    }



                    timeToStop(startTime)
                    stop()

                }
            }.start()

        } else {  //Переход на страницу статистики
            parentFragmentManager.beginTransaction().replace(R.id.fragment, Statistic()).commit()
            parentFragmentManager.beginTransaction().remove(MainWindow())
        }


    }

    private fun timeToStop(startTime: Long) {
        timer?.cancel()
        timer = object : CountDownTimer(startTime,1){
            override fun onTick(p0: Long) {

                binding.timer.text = (startTime - p0).toString()


            }
            override fun onFinish() {
               /* binding.viewText.text = mainList[wordNum]
                binding.btnReady.visibility = View.INVISIBLE
                binding.btnStop.visibility = View.VISIBLE*/

                binding.viewText.visibility = View.INVISIBLE
                binding.btnStop.visibility = View.INVISIBLE

                tempWordEtal = binding.viewText.text.toString()
                binding.info.text = "Введите слово (-а)"

                timer?.cancel()

                inputWord()

            }
        }.start()
    }



    private fun stop() {
        binding.btnStop.setOnClickListener {
            binding.viewText.visibility = View.INVISIBLE
            binding.btnStop.visibility = View.INVISIBLE

            tempWordEtal = binding.viewText.text.toString()

            binding.info.text = "Введите слово (-а)"


            timer?.cancel()


            inputWord()
        }

    }

    //Ввод слов для проверки
    private fun inputWord() {



        binding.btnNext.visibility = View.VISIBLE
        binding.shText.visibility = View.VISIBLE

        binding.btnNext.setOnClickListener {


            if (binding.shText.text.isNullOrEmpty()) {
                binding.info.text = "ВВЕДИТЕ СЛОВО!"
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
                    timeToReady(level)
                } else end()
            }
        }


    }

    private fun end() {
        binding.apply {
            editText.visibility = View.VISIBLE
            headLvlNum.visibility = View.VISIBLE
            lvlNum.visibility = View.VISIBLE
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
        binding.lvlNum.text = falseNum.toString()



    }


}
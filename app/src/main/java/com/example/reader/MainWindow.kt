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
import java.util.logging.Handler

var mainList = listOf<String>()
var wrongList = mutableListOf<String>()
var cash: String = ""
var origList = mutableListOf<String>()
lateinit var tempWord :String
lateinit var tempWordEtal :String
var wordNum = 0
var plusTime : Long = 0
var trueNum = 0
var falseNum = 0
var lev = 1
var number = 1

var progressStatus = 0
var handler = android.os.Handler()

//Входящие данные
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

        convertText()


        wrongList.clear()
        origList.clear()
        binding.apply {
            trueWin.text = null
            falseWin.text = null
            lvlNum.text = null
            numWords.text = null
            num.text = null
            size.text = null
            shText.text = null
            timer.text = null
            viewText.text = null
        }


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


        binding.lvlNum.text = "1"
        inputText()
    }


    //Конвертация текста
    private fun convertText(){
        binding.convert.setOnClickListener {
            if (binding.editText.text.isNullOrBlank()) {
                binding.info.visibility = View.VISIBLE
                binding.info.text = "Введите текст!"}
            else{
                var convText: String = binding.editText.text.toString()
                var txt: String = convText.replace("[!\"#$%&'()*+,./:;<=>?@\\[\\]^_`{|}~]".toRegex(), "")
                var txt2: String = txt.replace("[\n-]".toRegex(), " ")
                binding.editText.setText(txt2.lowercase())
                binding.convert.visibility = View.INVISIBLE
                binding.btnInput.visibility = View.VISIBLE
            }
        }


    }



    //Ввод текста
    private fun inputText() {

    binding.clear.setOnClickListener {
        binding.editText.text = null

    }

        binding.btnInput.setOnClickListener {
            if (binding.editText.text.isNullOrBlank()) {
                binding.info.visibility = View.VISIBLE
                binding.info.text = "Введите текст!"
            } else {

                var inText = binding.editText.text.toString()
                val delimeter = " "
                mainList = inText.split(delimeter)
                if (mainList.size < words){
                    binding.info.text = "Введено ${mainList.size}, надо не менее $words!"
                } else {

                binding.btnInput.visibility = View.INVISIBLE
                binding.editText.visibility = View.INVISIBLE
                    binding.clear.visibility = View.INVISIBLE

                binding.info.text = null

                ready()
                }

            }
        }
    }



//Готовность UI

    private fun ready() {
        binding.editText.text = null
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
                headLvlNum.visibility = View.VISIBLE
                lvlNum.visibility = View.VISIBLE


            }
            timeToReady(lev)

        }
    }



    //Обратный отсчет, вывод слов на экран из списка
    private fun timeToReady(level: Int) {
        if (((mainList.size - wordNum)/level) >= 1){   //Контроль уровня
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
                            binding.num.text = wordNum.toString()

                        }

                        3 -> {
                            binding.viewText.text = mainList[wordNum] + " " + mainList[wordNum + 1] + " " + mainList[wordNum + 2]
                            wordNum += 3
                            binding.num.text = wordNum.toString()

                        }

                        4 -> {
                            binding.viewText.text =
                                mainList[wordNum] + " " + mainList[wordNum + 1] + " "  + mainList[wordNum + 2] + " " + mainList[wordNum + 3]
                            wordNum += 4
                            binding.num.text = wordNum.toString()

                        }
                    }
                    cash = binding.viewText.text.toString()

                    levelControl()
                    timeToStop(startTime)
                    stop()

                }
            }.start()

        } else {  //Переход на страницу статистики

            //LifeData
            openModel.trueRez.value = binding.trueWin.text.toString()
            openModel.falseRez.value = binding.falseWin.text.toString()
            openModel.allWords.value = mainList.size.toString()
            openModel.wordsInMin.value = binding.numWords.text.toString().toInt()
            openModel.wrongWordsList.value = wrongList
            openModel.origWordsList.value = origList
            openModel.levels.value = level.toString()


            //Очистка
            wordNum = 0
            plusTime  = 0
            trueNum = 0
            falseNum = 0
            lev = 1
            number = 1





            parentFragmentManager.beginTransaction().replace(R.id.fragment, Statistic()).commit()

        }


    }


    //Контроль уровня
    private fun levelControl() {
       if (number < numExample) {
           number++
       } else if (number >= numExample) {
           number = 1
           if (lev < numLvl) {
               lev++
               startTime += timeplus
               binding.lvlNum.text = lev.toString()
           }

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

                tempWord = binding.shText.text.toString()
                if (binding.shText.text.toString() == tempWordEtal) {
                    when(lev) {
                        1 -> trueNum++
                        2 -> trueNum+=2
                        3 -> trueNum+=3
                        4 -> trueNum+=4

                    }
                    plusTime += binding.timer.text.toString().toLong()
                    binding.numWords.text = (trueNum * 60000 / plusTime).toString()
                    binding.trueWin.text = trueNum.toString()
                } else {
                    when(lev){
                        1 -> falseNum++
                        2 -> falseNum+=2
                        3 -> falseNum+=3
                        4 -> falseNum+=4
                    }

                    binding.falseWin.text = falseNum.toString()
                    wrongList.add(binding.shText.text.toString())
                    origList.add(cash)



                }

                binding.btnNext.visibility = View.INVISIBLE
                binding.info.text = null
                binding.timer.text = null
                binding.shText.text = null
                binding.viewText.text = null

                timeToReady(lev)

            }
        }
    }






}
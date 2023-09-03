package com.example.reader

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.example.reader.databinding.StatisticBinding
import kotlin.math.round

//Входящие данные:
var trueRezText: String = ""
var falseRezText: String = ""
var allWordsText: String = ""
var levels: String = ""
var wordsInMinText: Int = 0
var wrongWordsList = mutableListOf<String>()
var originalWordsList = mutableListOf<String>()


class Statistic : Fragment() {
    lateinit var binding: StatisticBinding
    private val openModel: OpenModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StatisticBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentFragmentManager.beginTransaction().remove(MainWindow())

        //LifeData
        openModel.trueRez.observe(activity as LifecycleOwner) {
            trueRezText = it
        }
        openModel.falseRez.observe(activity as LifecycleOwner) {
            falseRezText = it
        }
        openModel.allWords.observe(activity as LifecycleOwner) {
            allWordsText = it
        }
        openModel.wordsInMin.observe(activity as LifecycleOwner) {
            wordsInMinText = it
        }
        openModel.wrongWordsList.observe(activity as LifecycleOwner) {
            wrongWordsList = it
        }
        openModel.origWordsList.observe(activity as LifecycleOwner) {
            originalWordsList = it
        }
        openModel.levels.observe(activity as LifecycleOwner) {
            levels = it
        }



       binding.apply {
           trueRez.text = trueRezText.toString()
           falseRez.text = falseRezText.toString()
           allRez.text = allWordsText.toString()
           wordInMin.text = wordsInMinText.toString()
           lvls.text = levels


           var preGradle = 0
           var preGradle2 = 0

           if (wordsInMinText < 25) preGradle = 2
           else if (wordsInMinText in 26..39) preGradle = 3
           else if (wordsInMinText in 40..55) preGradle = 4
           else if (wordsInMinText > 55) preGradle = 5

           if (binding.trueRez.text.toString().toDouble() > binding.allRez.text.toString().toDouble() * 0.9) preGradle2 = 5
           if (binding.trueRez.text.toString().toDouble() >= (binding.allRez.text.toString().toDouble() * 0.8)
               && binding.trueRez.text.toString().toDouble() <= ( binding.allRez.text.toString().toDouble() * 0.9)) preGradle2 = 4
           if (binding.trueRez.text.toString().toDouble() >= (binding.allRez.text.toString().toDouble() * 0.6)
               && binding.trueRez.text.toString().toDouble() <= ( binding.allRez.text.toString().toDouble() * 0.79)) preGradle2 = 3
           if (binding.trueRez.text.toString().toDouble() < binding.allRez.text.toString().toDouble() * 0.59) {
              preGradle2 = 2
           }

          binding.grade.text = preGradle2.toString()
          binding.grade2.text = preGradle.toString()

           if (wrongWordsList.size == 0){
               wrongWords.text = "Нет ошибок"
               binding.falseRez.text = "0"
               binding.origWords.text = null
           } else {
               wrongWords.text = wrongWordsList.toString()
               origWords.text = originalWordsList.toString()
           }
       }

        binding.close.setOnClickListener {

            //Очистка
            trueRezText = ""
            falseRezText =  ""
            allWordsText = ""
            levels = ""
            wordsInMinText = 0
            binding.wrongWords.text = null
            binding.origWords.text = null


            //LifeData

            //LifeData
            openModel.trueRez.value = ""
            openModel.falseRez.value = ""
            openModel.allWords.value = ""
            openModel.wordsInMin.value = 0


            parentFragmentManager.beginTransaction().replace(R.id.fragment, Settings()).commit()

        }
    }




}
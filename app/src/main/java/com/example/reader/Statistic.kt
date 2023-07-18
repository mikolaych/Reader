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

//Входящие данные:
var trueRezText: String = ""
var falseRezText: String = ""
var allWordsText: String = ""
var wordsInMinText: Int = 0
var wrongWordsList = mutableListOf<String>()

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



       binding.apply {
           trueRez.text = trueRezText.toString()
           falseRez.text = falseRezText.toString()
           allRez.text = allWordsText.toString()
           wordInMin.text = wordsInMinText.toString()

           var preGradle = 0

           if (wordsInMinText < 25) grade.text = "2"
           else if (wordsInMinText in 26..39) grade.text = "3"
           else if (wordsInMinText in 40..55) grade.text = "4"
           else if (wordsInMinText > 55) grade.text = "5"


           if (wrongWordsList.size == 0){
               wrongWords.text = "Нет ошибок"
               binding.falseRez.text = "0"
           } else wrongWords.text = wrongWordsList.toString()
       }

        binding.close.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.fragment, Settings()).commit()
            parentFragmentManager.beginTransaction().remove(Statistic())
        }
    }




}
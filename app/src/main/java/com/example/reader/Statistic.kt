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
var trueRez: String = ""
var falseRez: String = ""
var allWords: String = ""
var wordsInMin: String = ""
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
            trueRez = it
        }
        openModel.falseRez.observe(activity as LifecycleOwner) {
            falseRez = it
        }
        openModel.allWords.observe(activity as LifecycleOwner) {
            allWords = it
        }
        openModel.wordsInMin.observe(activity as LifecycleOwner) {
            wordsInMin = it
        }
        openModel.wrongWordsList.observe(activity as LifecycleOwner) {
            wrongWordsList = it
        }
    }


}
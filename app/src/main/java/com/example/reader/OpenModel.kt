package com.example.reader

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class OpenModel: ViewModel() {

    //Для MainWindow
   val numLvl: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val numExercise: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val timer: MutableLiveData<Long> by lazy {
        MutableLiveData<Long>()
    }
    val timerPlus: MutableLiveData<Long> by lazy {
        MutableLiveData<Long>()
    }
    val words: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }


    //Для Statistic
    val trueRez: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val falseRez: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val allWords: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val wordsInMin: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val wrongWordsList: MutableLiveData<MutableList<String>> by lazy {
        MutableLiveData<MutableList<String>>()
    }
}
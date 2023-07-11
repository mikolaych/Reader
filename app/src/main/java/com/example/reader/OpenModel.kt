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
    val checkTimer: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
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
}
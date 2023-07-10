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

/*var mainList = listOf<String>()
var exampleArray = mutableListOf<String>()
lateinit var tempWord :String
lateinit var tempWordEtal :String
var wordNum = 0
var plusTime : Long = 0
var trueNum = 0
var falseNum = 0*/


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var timer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().replace(R.id.fragment, Settings()).commit()



    }






}
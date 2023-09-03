package com.example.reader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.view.View
import android.widget.Chronometer
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.reader.databinding.ActivityMainBinding
import kotlinx.coroutines.NonCancellable.start
import kotlin.system.measureTimeMillis




class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.fragment, Settings()).commit()


    }
}
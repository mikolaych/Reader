package com.example.reader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.reader.databinding.ActivityMainBinding
import kotlinx.coroutines.NonCancellable.start

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        startBtn()
    }

    private fun startBtn() {
        binding.btnStart.setOnClickListener {
            if (binding.editText.text.isNullOrBlank()) {
                binding.info.visibility = View.VISIBLE
                binding.info.text = "Введите текст!"
            } else if (binding.inputTimer.text.isNullOrBlank()) {
                binding.info.visibility = View.VISIBLE
                binding.info.text = "Введите таймер!"
            } else {
                visibilityOn()
            }
        }

    }

    private fun visibilityOn() {
        binding.headInputTime.visibility = View.INVISIBLE
        binding.inputTimer.visibility = View.INVISIBLE
        binding.editText.visibility = View.INVISIBLE
        binding.btnStart.visibility = View.INVISIBLE
        binding.viewText.visibility = View.VISIBLE
        binding.btnExam.visibility = View.VISIBLE
        binding.shText.visibility = View.VISIBLE
        binding.timer.visibility = View.VISIBLE
        binding.headFalse.visibility = View.VISIBLE
        binding.headTrue.visibility = View.VISIBLE
        binding.trueWin.visibility = View.VISIBLE
        binding.falseWin.visibility = View.VISIBLE
        binding.headGrade.visibility = View.VISIBLE
        binding.grade.visibility = View.VISIBLE
        binding.info.text = null

    }

    private fun visibilityOff() {
        binding.headInputTime.visibility = View.VISIBLE
        binding.inputTimer.visibility = View.VISIBLE
        binding.editText.visibility = View.VISIBLE
        binding.btnStart.visibility = View.VISIBLE
        binding.viewText.visibility = View.INVISIBLE
        binding.btnExam.visibility = View.INVISIBLE
        binding.shText.visibility = View.INVISIBLE
        binding.timer.visibility = View.INVISIBLE
        binding.headFalse.visibility = View.INVISIBLE
        binding.headTrue.visibility = View.INVISIBLE
        binding.trueWin.visibility = View.INVISIBLE
        binding.falseWin.visibility = View.INVISIBLE
        binding.headGrade.visibility = View.INVISIBLE
        binding.grade.visibility = View.INVISIBLE
        binding.info.text = null

    }
}
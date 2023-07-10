package com.example.reader

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.reader.databinding.SettingsBinding
import com.google.android.material.slider.Slider


class Settings : Fragment() {
    lateinit var binding: SettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SettingsBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            //Переключатель таймера
            switchTimer.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    timer.visibility = View.VISIBLE
                    timerPlus.visibility = View.VISIBLE
                    tittleTimer.visibility = View.VISIBLE
                    tittleTimerPlus.visibility = View.VISIBLE
                } else {
                    timer.visibility = View.INVISIBLE
                    timerPlus.visibility = View.INVISIBLE
                    tittleTimer.visibility = View.INVISIBLE
                    tittleTimerPlus.visibility = View.INVISIBLE
                }
            }

            //Подсчет количества слов

            lvlNum.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
                override fun onStartTrackingTouch(slider: Slider) {
                    writeWords()
                }
                override fun onStopTrackingTouch(slider: Slider) {
                    writeWords()
                }
            })

            examNum.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
                override fun onStartTrackingTouch(slider: Slider) {
                    writeWords()
                }
                override fun onStopTrackingTouch(slider: Slider) {
                    writeWords()
                }
            })

            }

    }

    private fun writeWords() {
        var words: Float = 0F

        when {
            binding.lvlNum.value == 1F -> words = binding.examNum.value
            binding.lvlNum.value == 2F -> words = binding.examNum.value + binding.examNum.value * 2
            binding.lvlNum.value == 3F -> words = binding.examNum.value + binding.examNum.value * 2 + binding.examNum.value * 3
            binding.lvlNum.value == 4F -> words = binding.examNum.value + binding.examNum.value * 2 + binding.examNum.value * 3 + binding.examNum.value * 4
        }
        binding.numWords.text = words.toInt().toString()
    }


}
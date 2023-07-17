package com.example.reader

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.reader.databinding.SettingsBinding
import com.google.android.material.slider.Slider


class Settings : Fragment() {
    lateinit var binding: SettingsBinding
    private val openModel: OpenModel by activityViewModels()

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

            //Переключение слайдеров

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

        btnSaveSettings()
        btnQuickGame()

    }

    //Быстрая игра
    private fun btnQuickGame() {
        binding.btnQuickGame.setOnClickListener {
            openModel.numExercise.value = 10
            openModel.numLvl.value = 2
            openModel.timer.value = 5000
            openModel.timerPlus.value = 3000
            openModel.words.value = 30
            openModel.checkTimer.value = true
            parentFragmentManager.beginTransaction().replace(R.id.fragment, MainWindow()).commit()
            parentFragmentManager.beginTransaction().remove(Settings())
        }
    }


    //Подсчет количества слов
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

    //

    //Кнопка "Сохранить настройки"
    private fun btnSaveSettings() {
        binding.apply {
            btnSaveSet.setOnClickListener {
                if (switchTimer.isChecked && (timer.text.isNullOrEmpty() || timerPlus.text.isNullOrEmpty())){
                    info.text = "Заполните поля таймера!"
                } else {
                    openModel.numExercise.value = binding.examNum.value.toInt()
                    openModel.numLvl.value = binding.lvlNum.value.toInt()
                    openModel.timer.value = binding.timer.text.toString().toLong() * 1000
                    openModel.timerPlus.value = binding.timerPlus.text.toString().toLong() * 1000
                    openModel.words.value = binding.numWords.text.toString().toInt()
                    openModel.checkTimer.value = binding.switchTimer.isChecked
                    parentFragmentManager.beginTransaction().replace(R.id.fragment, MainWindow()).commit()
                    parentFragmentManager.beginTransaction().remove(Settings())




                }

            }
        }
    }



}
package com.example.reader

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.reader.databinding.StatisticBinding


class Statistic : Fragment() {
    lateinit var binding: StatisticBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StatisticBinding.inflate(inflater)
        return binding.root
    }


}
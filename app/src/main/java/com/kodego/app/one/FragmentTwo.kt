package com.kodego.app.one

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kodego.app.one.databinding.FragmentOneBinding
import com.kodego.app.one.databinding.FragmentTwoBinding


class FragmentTwo : Fragment() {
    lateinit var binding: FragmentTwoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTwoBinding.inflate(layoutInflater)
        var textFromParent = arguments?.getString("data2")
        binding.tvFragment2.text = textFromParent


        return binding.root
    }


}
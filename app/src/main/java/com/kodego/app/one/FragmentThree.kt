package com.kodego.app.one

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kodego.app.one.databinding.FragmentOneBinding
import com.kodego.app.one.databinding.FragmentThreeBinding
import com.kodego.app.one.databinding.FragmentTwoBinding


class FragmentThree : Fragment() {
    lateinit var binding: FragmentThreeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThreeBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

}
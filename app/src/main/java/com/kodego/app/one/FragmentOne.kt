package com.kodego.app.one

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kodego.app.one.databinding.FragmentOneBinding


class FragmentOne : Fragment() {

    lateinit var binding: FragmentOneBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }


}
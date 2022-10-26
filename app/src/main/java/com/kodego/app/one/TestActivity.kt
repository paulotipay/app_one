package com.kodego.app.one

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import android.widget.Toast.makeText
import com.kodego.app.one.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentOne = FragmentOne()
        val fragmentTwo = FragmentTwo()
        val fragmentThree = FragmentThree()

        //initial fragment
        supportFragmentManager.beginTransaction().apply{
            replace(binding.fragmentMain.id,fragmentOne)
            commit()
        }

        binding.btnFragment1.setOnClickListener(){
            supportFragmentManager.beginTransaction().apply{
                replace(binding.fragmentMain.id,fragmentOne)

                commit()
            }
        }

        binding.btnFragment2.setOnClickListener(){
            supportFragmentManager.beginTransaction().apply{
                replace(binding.fragmentMain.id,fragmentTwo)

                commit()
            }
        }

        binding.btnFragment3.setOnClickListener(){
            supportFragmentManager.beginTransaction().apply{
                replace(binding.fragmentMain.id,fragmentThree)

                commit()
            }
        }
    }

}
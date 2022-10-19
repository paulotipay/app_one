package com.kodego.app.one

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kodego.app.one.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //get data from another screen
        var name:String? = intent.getStringExtra("nameID")

        binding.tvWelcome.text = "Welcome $name"
    }
}
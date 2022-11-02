package com.kodego.app.one

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import android.widget.Toast.makeText
import com.google.android.material.tabs.TabLayout
import com.kodego.app.one.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var myData1 = "This is data 1"
        var myData2 = "This is data 2"
        var myData3 = "This is data 3"


        val fragmentOne = FragmentOne()
        val fragmentTwo = FragmentTwo()
        val fragmentThree = FragmentThree()

        var bundle1 = Bundle()
        bundle1.putString("data1", myData1)
        fragmentOne.arguments = bundle1

        var bundle2 = Bundle()
        bundle2.putString("data2", myData2)
        fragmentTwo.arguments = bundle2

        var bundle3 = Bundle()
        bundle3.putString("data3", myData3)
        fragmentThree.arguments = bundle3

        //initial fragment
        supportFragmentManager.beginTransaction().apply{
            replace(binding.fragmentMain.id,fragmentOne)
            commit()
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab?.position == 0){
                    supportFragmentManager.beginTransaction().apply{
                        replace(binding.fragmentMain.id,fragmentOne)
                        commit()
                    }
                }else if(tab?.position == 1) {
                    supportFragmentManager.beginTransaction().apply{
                        replace(binding.fragmentMain.id,fragmentTwo)
                        commit()
                    }
                }else if(tab?.position == 2) {
                    supportFragmentManager.beginTransaction().apply{
                        replace(binding.fragmentMain.id,fragmentThree)

                        commit()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

}
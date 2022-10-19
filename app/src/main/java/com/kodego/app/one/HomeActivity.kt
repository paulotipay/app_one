package com.kodego.app.one

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.kodego.app.one.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //data source
        var productList = mutableListOf<Products>(
            Products(R.drawable.ic_baseline_car_repair_24,"Car Parts", "This is a package for different car parts"),
            Products(R.drawable.ic_baseline_coffee_24,"Coffee", "Hot Coffee"),
            Products(R.drawable.ic_baseline_colorize_24,"Pen", "This is a premium pen"),
            Products(R.drawable.ic_baseline_car_repair_24,"Car Parts", "This is a package for different car parts"),
            Products(R.drawable.ic_baseline_coffee_24,"Coffee", "Hot Coffee"),
            Products(R.drawable.ic_baseline_colorize_24,"Pen", "This is a premium pen"),
            Products(R.drawable.ic_baseline_car_repair_24,"Car Parts", "This is a package for different car parts"),
            Products(R.drawable.ic_baseline_coffee_24,"Coffee", "Hot Coffee"),
            Products(R.drawable.ic_baseline_colorize_24,"Pen", "This is a premium pen"),
            Products(R.drawable.ic_baseline_car_repair_24,"Car Parts", "This is a package for different car parts"),
            Products(R.drawable.ic_baseline_coffee_24,"Coffee", "Hot Coffee"),
            Products(R.drawable.ic_baseline_colorize_24,"Pen", "This is a premium pen"),
            Products(R.drawable.ic_baseline_car_repair_24,"Car Parts", "This is a package for different car parts"),
            Products(R.drawable.ic_baseline_coffee_24,"Coffee", "Hot Coffee"),
            Products(R.drawable.ic_baseline_colorize_24,"Pen", "This is a premium pen"),
            Products(R.drawable.ic_baseline_car_repair_24,"Car Parts", "This is a package for different car parts"),
            Products(R.drawable.ic_baseline_coffee_24,"Coffee", "Hot Coffee"),
            Products(R.drawable.ic_baseline_colorize_24,"Pen", "This is a premium pen")
        )

        //pass data source to adapter
        val adapter = ProductAdapter(productList)

        binding.myRecycler.adapter = adapter
        binding.myRecycler.layoutManager = LinearLayoutManager(this)

        //get data from another screen
//        var name:String? = intent.getStringExtra("nameID")
//
//        binding.tvWelcome.text = "Welcome $name"
    }
}
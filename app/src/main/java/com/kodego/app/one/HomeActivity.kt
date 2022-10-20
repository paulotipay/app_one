package com.kodego.app.one

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.kodego.app.one.databinding.ActivityHomeBinding
import com.kodego.app.one.databinding.LayoutDialogBinding

class HomeActivity : AppCompatActivity() {
    lateinit var productList : MutableList<Products>
    lateinit var binding : ActivityHomeBinding
    lateinit var adapter : ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //data source
        productList = mutableListOf<Products>(
            Products(R.drawable.ic_baseline_car_repair_24,"Car Parts", "This is a package for different car parts",10),
            Products(R.drawable.ic_baseline_coffee_24,"Coffee", "Hot Coffee",2),
            Products(R.drawable.ic_baseline_colorize_24,"Pen", "This is a premium pen",3)
        )


        //pass data source to adapter
        adapter = ProductAdapter(productList)
        adapter.onItemClick = {
            val name:String = it.itemName
            Toast.makeText(applicationContext,name, Toast.LENGTH_LONG).show()
        }

        //button
//        adapter.onClick = {
//            val quantity:String = it.quantity.toString()
//            var newQty:Int =  showMessageBox(it.quantity.toString().toInt())
//            Toast.makeText(applicationContext,""+newQty, Toast.LENGTH_LONG).show()
//        }
        adapter.onClick ={ pos:Int, it:Products ->
            val position = pos
//            showMessageBox(it,pos)

        }
        binding.myRecycler.adapter = adapter
        binding.myRecycler.layoutManager = LinearLayoutManager(this)



        //get data from another screen
//        var name:String? = intent.getStringExtra("nameID")
//
//        binding.tvWelcome.text = "Welcome $name"
    }


    private fun showMessageBox(products:Products,position:Int){
        var newQty: Int
        val dialog = Dialog(this)
        dialog.setCancelable(false)
        val bind :LayoutDialogBinding = LayoutDialogBinding.inflate(layoutInflater)
        dialog.setContentView(bind.root)
        dialog.show()

        bind.btnDialog.setOnClickListener(){
            newQty = bind.etDialogNewQty.text.toString().toInt()
            products.quantity = newQty

            productList.add(products)
            adapter.notifyItemInserted(position)
            Toast.makeText(applicationContext,"added"+position,Toast.LENGTH_SHORT).show()

            productList.removeAt(position+1)
            adapter.notifyItemRemoved(position+1)
            Toast.makeText(applicationContext,"${adapter.itemCount}",Toast.LENGTH_SHORT).show()



            dialog.dismiss()
        }

    }
}

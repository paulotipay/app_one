package com.kodego.app.one

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kodego.app.one.databinding.ActivityHomeBinding
import com.kodego.app.one.databinding.AddDialogBinding
import com.kodego.app.one.databinding.QuantityDialogBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityHomeBinding
    lateinit var adapter : ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //data source
        var productList : MutableList<Products> = mutableListOf<Products>(
            Products(R.drawable.ic_baseline_car_repair_24,"Car Parts", "This is a package for different car parts",1),
            Products(R.drawable.ic_baseline_coffee_24,"Coffee", "Hot Coffee",2),
            Products(R.drawable.ic_baseline_colorize_24,"Pen", "This is a premium pen",5),
            Products(R.drawable.ic_baseline_car_repair_24,"Car Parts", "This is a package for different car parts",8),
            Products(R.drawable.ic_baseline_coffee_24,"Coffee", "Hot Coffee",19),
            Products(R.drawable.ic_baseline_colorize_24,"Pen", "This is a premium pen",21),
        )



        //pass data source to adapter
        adapter = ProductAdapter(productList)
        adapter.onItemClick = {
            val intent = Intent(this,ProductDetailActivity::class.java)
            intent.putExtra("itemName",it.itemName)
            intent.putExtra("itemDescription",it.itemDescription)
            intent.putExtra("itemImage",it.imageName)
            intent.putExtra("quantity",it.quantity)
            startActivity(intent)
        }

        adapter.onUpdateButtonClick = { item:Products, position:Int ->
            showUpdateDialog(item, position)
        }

        adapter.onDeleteButtonClick = { item:Products, position:Int ->
            adapter.products.removeAt(position)
            adapter.notifyDataSetChanged()
        }

        binding.floatingActionButton.setOnClickListener(){
            showAddDialog()
        }


        binding.myRecycler.adapter = adapter
        binding.myRecycler.layoutManager = LinearLayoutManager(this)

    }
    fun showUpdateDialog(item:Products, position: Int){
        val dialog = Dialog(this)
        val binding: QuantityDialogBinding = QuantityDialogBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        dialog.show()

        binding.btnUpdateQty.setOnClickListener(){
            var newQuantity : Int = binding.etQtyDialog.text.toString().toInt()
            adapter.products[position].quantity = newQuantity
            adapter.notifyDataSetChanged()
            dialog.dismiss()
        }
    }

    fun showAddDialog(){
        val dialog = Dialog(this)
        val binding: AddDialogBinding = AddDialogBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        dialog.show()

        val images = arrayListOf<String>("ic_baseline_car_repair_24",
                "ic_baseline_coffee_24",
                "ic_baseline_colorize_24",
                "ic_baseline_list_alt_24",
                "ic_baseline_lock_24",
                "ic_baseline_person_24"
        )

        val spinnerAdapter = ArrayAdapter(applicationContext,R.layout.textview_xml,images)
        binding.spnImage.adapter = spinnerAdapter



        binding.btnAdd.setOnClickListener(){

            var image :Int = resources.getIdentifier(binding.spnImage.selectedItem.toString(),"drawable",packageName)
            var itemName :String = binding.etAddDialogName.text.toString()
            var itemDesc : String = binding.etAddDialogDesc.text.toString()
            var quantity = binding.etDialogQty.text.toString().toInt()

            adapter.products.add(Products(image,itemName,itemDesc,quantity))
            adapter.notifyItemInserted(adapter.itemCount+1)
            dialog.dismiss()

        }



    }

}
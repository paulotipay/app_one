package com.kodego.app.one

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.kodego.app.one.databinding.ActivityHomeBinding
import com.kodego.app.one.databinding.AddDialogBinding
import com.kodego.app.one.databinding.ImageSelectionDialogBinding
import com.kodego.app.one.databinding.QuantityDialogBinding
import java.io.ByteArrayOutputStream

class HomeActivity : AppCompatActivity() {
    private lateinit var  addDialogBinding : AddDialogBinding
    private lateinit var thumbnail : Bitmap
    lateinit var binding : ActivityHomeBinding
    lateinit var adapter : ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //data source
        var productList : MutableList<Products> = mutableListOf<Products>(
//            Products(R.drawable.ic_baseline_car_repair_24,"Car Parts", "This is a package for different car parts",1),
//            Products(R.drawable.ic_baseline_coffee_24,"Coffee", "Hot Coffee",2),
//            Products(R.drawable.ic_baseline_colorize_24,"Pen", "This is a premium pen",5),
//            Products(R.drawable.ic_baseline_car_repair_24,"Car Parts", "This is a package for different car parts",8),
//            Products(R.drawable.ic_baseline_coffee_24,"Coffee", "Hot Coffee",19),
//            Products(R.drawable.ic_baseline_colorize_24,"Pen", "This is a premium pen",21),
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
        addDialogBinding = AddDialogBinding.inflate(layoutInflater)
        dialog.setContentView(addDialogBinding.root)
        dialog.show()

        val images = arrayListOf<String>("ic_baseline_car_repair_24",
                "ic_baseline_coffee_24",
                "ic_baseline_colorize_24",
                "ic_baseline_list_alt_24",
                "ic_baseline_lock_24",
                "ic_baseline_person_24"
        )

        val spinnerAdapter = ArrayAdapter(applicationContext,R.layout.textview_xml,images)
        addDialogBinding.spnImage.adapter = spinnerAdapter

        addDialogBinding.imageButton.setOnClickListener(){
            showImageDialog()
        }


        addDialogBinding.btnAdd.setOnClickListener(){



            val bitmap = (thumbnail)
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG,90,stream)
            val image = stream.toByteArray()

            var itemName :String = addDialogBinding.etAddDialogName.text.toString()
            var itemDesc : String = addDialogBinding.etAddDialogDesc.text.toString()
            var quantity = addDialogBinding.etDialogQty.text.toString().toInt()

            adapter.products.add(Products(image,itemName,itemDesc,quantity))
            adapter.notifyItemInserted(adapter.itemCount+1)

            var imageTest: Resources = addDialogBinding.imgPic.resources


            Toast.makeText(applicationContext, ""+imageTest, Toast.LENGTH_SHORT).show()
            dialog.dismiss()

        }

    }
    fun showImageDialog(){
        Toast.makeText(this@HomeActivity, "Image Dialog", Toast.LENGTH_SHORT).show()
        val dialog = Dialog(this)
        val binding : ImageSelectionDialogBinding = ImageSelectionDialogBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)


        binding.ivCamera.setOnClickListener(){
            Toast.makeText(this@HomeActivity,"Test",Toast.LENGTH_SHORT).show()
            Dexter.withContext(this).withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    report?.let{
                        if(report.areAllPermissionsGranted()){
                            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                            resultLauncher.launch(intent)
                        }
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?,
                    p1: PermissionToken?
                ) {
                    Toast.makeText(this@HomeActivity,"Permissions are NOT granted",Toast.LENGTH_SHORT).show()
                }

            }).onSameThread().check()

            dialog.dismiss()
        }


        dialog.show()
    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())  { result->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.extras?.let {
                thumbnail = result.data?.extras?.get("data") as Bitmap
                addDialogBinding.imgPic.setImageBitmap(thumbnail)
            }
        }
    }

}
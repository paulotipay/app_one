package com.kodego.app.one

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.widget.ArrayAdapter
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.tabs.TabLayout
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.kodego.app.one.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCamera.setOnClickListener(){
            showCamera()
        }

        binding.btnGallery.setOnClickListener(){
            showGallery()
        }

    }

    private fun showGallery() {
        Dexter.withContext(this).withPermission(
            Manifest.permission.READ_EXTERNAL_STORAGE
        ).withListener(object: PermissionListener{
            override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                galleryLauncher.launch(galleryIntent)
            }

            override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                Toast.makeText(applicationContext,"Gallery Permission Denied",Toast.LENGTH_SHORT).show()
                gotoSettings()
            }

            override fun onPermissionRationaleShouldBeShown(
                request: PermissionRequest?,
                token: PermissionToken?
            ) {
                token?.continuePermissionRequest()
            }

        }).onSameThread().check()
    }

    private fun showCamera() {
        Dexter.withContext(this).withPermission(
            Manifest.permission.CAMERA
        ).withListener(object : PermissionListener{
            override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//                startActivity(cameraIntent)
                cameraLauncher.launch(cameraIntent)
                Toast.makeText(applicationContext,"Permission Granted",Toast.LENGTH_SHORT).show()
            }

            override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                Toast.makeText(applicationContext,"Permission Denied",Toast.LENGTH_SHORT).show()
                gotoSettings()
            }

            override fun onPermissionRationaleShouldBeShown(
                request: PermissionRequest?,
                token: PermissionToken?
            ) {
                token?.continuePermissionRequest()
            }

        }).onSameThread().check()
    }

    private fun gotoSettings() {
        AlertDialog.Builder(this).setMessage("It seems that your permission has been denied. Go to settings to enable permission.")
            .setPositiveButton("Go to Settings"){ dialog, item ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                var uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
            }.setNegativeButton("Cancel"){ dialog, item ->
                dialog.dismiss()
            }.show()
    }

    //handles images from the camera
    val cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if(result.resultCode == Activity.RESULT_OK){
            result.data?.extras.let{
                val image : Bitmap = result.data?.extras?.get("data") as Bitmap
                binding.ivImage.setImageBitmap(image)
            }
        }
    }
    //handles images from gallery
    val galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == Activity.RESULT_OK){
            result.data?.let{
                val selectedImage = result.data?.data
                binding.ivImage.setImageURI(selectedImage)
            }
        }
    }
}
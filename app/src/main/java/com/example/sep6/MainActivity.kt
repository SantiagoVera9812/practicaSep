package com.example.sep6

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.example.sep6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    val getContentGallery = registerForActivityResult(ActivityResultContracts.GetContent(),
        ActivityResultCallback { loadImage(it!!) })

    val getContentCamera = registerForActivityResult(ActivityResultContracts.TakePicture(), ActivityResultCallback {
        loadImage(cameraUri)
    })
    lateinit var cameraUri: Uri
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.gallerybutton.setOnClickListener{

            getContentGallery.launch("images/*")
        }

        binding.camerabutton.setOnClickListener{

            getContentCamera.launch(cameraUri)
        }

        binding.Canciones.setOnClickListener{

            startActivity(Intent(baseContext, ContactsActivity::class.java))
        }
    }

    fun loadImage(uri: Uri){

        val imageStream = contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(imageStream)
        binding.image.setImageBitmap(bitmap)


    }
}
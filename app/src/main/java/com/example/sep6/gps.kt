package com.example.sep6

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.example.sep6.databinding.ActivityGpsBinding
import com.example.sep6.modelo.MyLocation
import com.google.android.gms.location.FusedLocationProviderClient

import com.google.android.gms.location.LocationServices
import java.util.Date

class gps : AppCompatActivity() {


    val getPermission = registerForActivityResult(ActivityResultContracts.RequestPermission(),
        ActivityResultCallback { updateUI() })
    private lateinit var binding: ActivityGpsBinding
    private lateinit var locationClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGpsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        locationClient = LocationServices.getFusedLocationProviderClient(this);
        checkLocationPermission()

        binding.ir.setOnClickListener{}

        startActivity(Intent(this, LocationAware::class.java))

    }

    fun checkLocationPermission(){

        if((checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)) == PackageManager.PERMISSION_DENIED )
            if(shouldShowRequestPermissionRationale(android.Manifest.permission.ACCESS_FINE_LOCATION)){

                binding.altitud.text = "Permiso requerido para altitud"
                binding.latitud.text = "Permiso requerido para latitud"
                binding.longitud.text = "Permiso requerido para longitud"

            }

        getPermission.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
    }



    fun updateUI(){

        if((checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION))== PackageManager.PERMISSION_DENIED){
         locationClient.lastLocation.addOnSuccessListener {

             if(it != null){
                 binding.altitud.text = it.altitude.toString()
                 binding.latitud.text = it.latitude.toString()
                 binding.longitud.text = it.longitude.toString()

                 Log.i("LocationKT", "latitud: ${it.latitude}")
                 Log.i("LocationAlt", "altitud: ${it.altitude}")
                 Log.i("LocationLong", "longitud: ${it.longitude}")


             }
         }
        }
    }




}
package com.example.sep6

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.example.sep6.databinding.ActivityLocationAwareBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult

class LocationAware : AppCompatActivity() {

    private lateinit var binding: ActivityLocationAwareBinding

    private lateinit var localClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private lateinit var lastLocation: Location
    val getPermission = registerForActivityResult(ActivityResultContracts.RequestPermission(), ActivityResultCallback { updateUI() })
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationAwareBinding.inflate(layoutInflater)
        setContentView(binding.root)

        locationRequest = createLocationRequest()
        locationCallback = createLocationCallback()

    }

    private fun createLocationCallback(): LocationCallback {

        val locationCallback = object : LocationCallback(){

            override fun onLocationResult(p0: LocationResult) {
                super.onLocationResult(p0)
                if(p0 != null ){

                    val location = p0.lastLocation!!
                    updateUI()
                }
            }
        }
    return locationCallback
    }

    private fun updateUI() {



            if (lastLocation != null) {


                binding.altitud.text = lastLocation.altitude.toString()
                binding.latitud.text = lastLocation.longitude.toString()
                binding.longitud.text = lastLocation.latitude.toString()
                Log.i("LocationT", "altitud: ${lastLocation.altitude}")
                Log.i("LocationK", "latitud ${lastLocation.latitude}")
                Log.i("Location", "Longitud: ${lastLocation.longitude}")
            }

    }

    fun createLocationRequest():LocationRequest{

        locationRequest = LocationRequest.create()
            .setInterval(10000)
            .setFastestInterval(5000)
            .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)

        return locationRequest
    }


    fun checkLocationPermission(){

        if((checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)) == PackageManager.PERMISSION_DENIED)
            if(shouldShowRequestPermissionRationale(android.Manifest.permission.ACCESS_FINE_LOCATION)){
                binding.latitud.text = "se requierte gps para"
            }

        getPermission.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
    }

    val getLocationSettings = registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult(),
        ActivityResultCallback {
            if(it.resultCode == RESULT_OK){
                startLocationUpdates()
            }
            else{

                binding.altitud.text = "Apagado"
            }
        })
    private fun startLocationUpdates() {

        if((checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)) == PackageManager.PERMISSION_GRANTED){

            localClient.requestLocationUpdates()

        }
    }


}
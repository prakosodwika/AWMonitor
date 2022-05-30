package com.c22_ce02.awmonitorapp.utils

import android.Manifest
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.c22_ce02.awmonitorapp.R
import com.c22_ce02.awmonitorapp.databinding.ActivityHomeBinding
import com.c22_ce02.awmonitorapp.databinding.FragmentHomeBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import java.util.concurrent.TimeUnit


val Fragment.resolutionLauncher: ActivityResultLauncher<IntentSenderRequest>
    get() = registerForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        when (result.resultCode) {
            RESULT_OK ->
                return@registerForActivityResult
            RESULT_CANCELED ->
                showToast("Anda harus mengaktifkan GPS untuk menggunakan aplikasi ini!")
        }
    }

private val locationRequest = LocationRequest.create().apply {
    interval = TimeUnit.SECONDS.toMillis(1)
    maxWaitTime = TimeUnit.SECONDS.toMillis(1)
    priority = LocationRequest.PRIORITY_HIGH_ACCURACY
}

/*
fun Fragment.createLocationRequest(fusedLocationClient: FusedLocationProviderClient) {
    val builder = LocationSettingsRequest.Builder()
        .addLocationRequest(locationRequest)
    val client = LocationServices.getSettingsClient(requireActivity())
    client.checkLocationSettings(builder.build())
        .addOnSuccessListener {
            if (isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION) &&
                isPermissionGranted(Manifest.permission.ACCESS_COARSE_LOCATION)
            ) {
                if (ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return@addOnSuccessListener
                }
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        if (location != null) {
                            //onGetLocation(location.latitude, location.longitude)
                        } else {
                            showToast("lokasi null")
                        }
                    }
                    .addOnFailureListener {
                        showToast("Tidak dapat menemukan lokasi. Silakan Coba Lagi")
                    }
            } else {
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        }
}*/
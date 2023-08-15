package com.example.codsoft_task1

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.camera2.CameraCharacteristics.FLASH_INFO_AVAILABLE
import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    val camMan by lazy{
        getSystemService(Context.CAMERA_SERVICE) as CameraManager
    }
    var camIdwithFlash : String = "0"
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val camList = camMan.cameraIdList
        camList.forEach {
            val characteristic = camMan.getCameraCharacteristics(it)
            val doesCamHasFlash : Boolean? = characteristic.get(FLASH_INFO_AVAILABLE)
            if(camIdwithFlash ==  "0" && doesCamHasFlash == true)
            {
                camIdwithFlash = it
            }
        }
        val button : Button = findViewById(R.id.btn)
        button.setOnClickListener {
            camMan.setTorchMode(camIdwithFlash, true)
        }

        val button2 : Button = findViewById(R.id.btn2)
        button2.setOnClickListener {
            camMan.setTorchMode(camIdwithFlash, false)
        }
    }

    override fun onPause() {
        super.onPause()
        camMan.setTorchMode(camIdwithFlash, false)
    }
}
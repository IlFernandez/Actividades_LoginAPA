package com.example.loginapa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val datosRecibidos = intent.extras
        val correo = datosRecibidos?.getString("correo")
        val contrasena = datosRecibidos?.getString("contrasena")

        val background = object:Thread(){
            override fun run(){
                try {
                    Thread.sleep(1000)
                    val intent = Intent(baseContext, LoginActivity::class.java)

                    intent.putExtra("correo",correo.toString())
                    intent.putExtra("contrasena",contrasena.toString())

                    startActivity(intent)
                    finish()
                } catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }
}
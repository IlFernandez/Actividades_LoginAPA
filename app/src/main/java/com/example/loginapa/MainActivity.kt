package com.example.loginapa

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_registro.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datosRecibidos = intent.extras
        val correo = datosRecibidos?.getString("correo")
        val contrasena = datosRecibidos?.getString("contrasena")
        tv_correo.text=correo
        tv_contrasena.text=contrasena
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_overflow, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.cerrar_sesion) {
            val intent=Intent(this, LoginActivity::class.java)
            intent.putExtra("correo",tv_correo.text.toString())
            intent.putExtra("contrasena",tv_contrasena.text.toString())
            startActivity(intent)
            Toast.makeText(this,"Salida exitosa", Toast.LENGTH_LONG).show()
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        val intent=Intent(this, SplashActivity::class.java)
        intent.putExtra("correo",tv_correo.text.toString())
        intent.putExtra("contrasena",tv_contrasena.text.toString())
        startActivity(intent)
        finish()
    }

    override fun onRestart() {
        super.onRestart()
        val intent=Intent(this, SplashActivity::class.java)
        intent.putExtra("correo",tv_correo.text.toString())
        intent.putExtra("contrasena",tv_contrasena.text.toString())
        startActivity(intent)
        finish()
    }
}
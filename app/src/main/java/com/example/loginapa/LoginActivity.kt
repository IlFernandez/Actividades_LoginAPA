package com.example.loginapa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.et_correo_login
import kotlinx.android.synthetic.main.activity_registro.*
import kotlin.system.exitProcess

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        bt_registrarse.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
        bt_iniciar.setOnClickListener {
            val correo = et_correo_login.text.toString()
            val contrasena = et_contrasena_login.text.toString()
            var datosRecibidos = intent.extras
            var comp_correo = datosRecibidos?.getString("correo")
            var comp_contrasena = datosRecibidos?.getString("contrasena")
            if (correo.isEmpty()){
                Toast.makeText(this, "Ingrese correo",Toast.LENGTH_SHORT).show()
            }
            else{
                if (contrasena.isEmpty()){
                    Toast.makeText(this, "Ingrese contraseña",Toast.LENGTH_SHORT).show()
                }
                else{
                    if (correo==comp_correo){
                        if (contrasena==comp_contrasena){
                            val intent = Intent(this, MainActivity::class.java)
                            Toast.makeText(this, "Ingreso correcto",Toast.LENGTH_SHORT).show()
                            intent.putExtra("correo",et_correo_login.text.toString())
                            intent.putExtra("contrasena",et_contrasena_login.text.toString())
                            startActivity(intent)
                            finish()
                        }
                        else{
                            Toast.makeText(this, "Contraseña incorrecta",Toast.LENGTH_SHORT).show()
                        }
                    }
                    else {
                        Toast.makeText(this, "Correo incorrecto",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
        exitProcess(0)
    }
}
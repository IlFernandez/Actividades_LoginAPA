package com.example.loginapa

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.android.synthetic.main.activity_registro.*

class RegistroActivity : AppCompatActivity() {
    private  lateinit var fecha: String
    private var cal = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val dataSetListener = object: DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR,year)
                cal.set(Calendar.MONTH,month)
                cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                val format="dd/MM/yyyy"
                var simpleDateFormat = SimpleDateFormat(format, Locale.US)
                fecha = simpleDateFormat.format(cal.time).toString()
                tv_fecha.text=fecha
            }
        }
        ib_calendario.setOnClickListener {
            DatePickerDialog(this,
                dataSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        bt_guardar.setOnClickListener {
            val nombre = et_nombre.text.toString()
            val cedula = et_cedula.text.toString()
            val correo = et_correo.text.toString()
            val telefono = et_telefono.text.toString()
            val contrasena = et_contrasena.text.toString()
            val rep_contrasena = et_rep_contrasena.text.toString()
            val ciudad = sp_ciudad_nacimiento.getSelectedItem() as String
            val com_fecha = tv_fecha.text.toString()
            val genero = if (rb_masculino.isChecked) "Masculino" else "Femenino"
            var pasatiempos = ""
            if (contrasena==rep_contrasena){
                if (nombre.isEmpty()){
                    tv_resultado.text="Ingrese su nombre para continuar con el registro."
                    et_nombre.setError("Ingrese un nombre")
                }
                else{
                    if (cedula.isEmpty()){
                        tv_resultado.text="Ingrese su cédula para continuar con el registro."
                        et_cedula.setError("Ingrese un número de cédula")
                    }
                    else{
                        if (correo.isEmpty()){
                            tv_resultado.text="Ingrese su correo para continuar con el registro."
                            et_correo.setError("Ingrese un correo")
                        }
                        else{
                            if (telefono.isEmpty()){
                                tv_resultado.text="Ingrese su telefono para continuar con el registro."
                                et_telefono.setError("Ingrese un número de teléfono")
                            }
                            else{
                                if (contrasena.isEmpty()){
                                    tv_resultado.text="Ingrese una contraseña para continuar con el registro."
                                    et_contrasena.setError("Ingrese una contraseña")
                                }
                                else{
                                    if (rep_contrasena.isEmpty()){
                                        tv_resultado.text="Ingrese nuevamente su contraseña para continuar con el registro."
                                        et_rep_contrasena.setError("Repita la contraseña")
                                    }
                                    else{
                                        if (ch_leer.isChecked||ch_dormir.isChecked||ch_deportes.isChecked||ch_videojuegos.isChecked){
                                            if (ch_dormir.isChecked) pasatiempos = "$pasatiempos Dormir"
                                            if (ch_leer.isChecked) pasatiempos = "$pasatiempos Leer"
                                            if (ch_videojuegos.isChecked) pasatiempos = "$pasatiempos Videojuegos"
                                            if (ch_deportes.isChecked) pasatiempos = "$pasatiempos Deportes"
                                            if (ciudad.isEmpty()){
                                                tv_resultado.text="Seleccione una ciudad de nacimiento para continuar con el registro."
                                            }
                                            else{
                                                if(com_fecha=="DD/MM/YYYY"){
                                                    tv_resultado.text="Seleccione una fecha de nacimiento para continuar con el registro."
                                                }
                                                else{
                                                    if(contrasena.length<6){
                                                        tv_resultado.text="Contraseña demasiado corta, debe contener al menos 6 dígitos."
                                                        et_contrasena.setError("Contraseña debe contener mínimo seis dígitos")
                                                    }
                                                    else{
                                                        val intent=Intent(this, LoginActivity::class.java)
                                                        intent.putExtra("correo",et_correo.text.toString())
                                                        intent.putExtra("contrasena",et_contrasena.text.toString())
                                                        Toast.makeText(this,"Registro completo",Toast.LENGTH_LONG).show()
                                                        startActivity(intent)
                                                        finish()
                                                    }
                                                }
                                            }
                                        }
                                        else{
                                            tv_resultado.text="Seleccione un pasatiempo para continuar con el registro."
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else{
                tv_resultado.text="Las contraseñas no coinciden, intente de nuevo por favor."
                et_rep_contrasena.setError("Las contraseñas no coinciden.")
            }
        }
    }
}
package com.example.serviciohora


import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val servicio = ShowTime::class.java
        val i = Intent(applicationContext,servicio)
        
        btnPlay.setOnClickListener{
            if(!MyService(servicio)){
                startService(i)
                Toast.makeText(applicationContext,"¡El servicio se ha iniciado correctamente!", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(applicationContext,"¡El servicio ya está inicializado!",Toast.LENGTH_SHORT).show()
            }
        }

        btnStop.setOnClickListener {
            if(MyService(servicio)){
                stopService(i)
                Toast.makeText(applicationContext,"¡El servicio se ha detenido!", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(applicationContext,"¡El servicio ya ha sido detenido corectamente!",Toast.LENGTH_SHORT).show()
            }
        }

        btnStatus.setOnClickListener {
            if(MyService(servicio)){
                Toast.makeText(applicationContext,"¡El servicio está ejecutándose correctamente!", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(applicationContext,"¡El servicio está detenido!",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun MyService(servicio: Class<*>): Boolean {
        val activityMan = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        for (service in activityMan.getRunningServices(Integer.MAX_VALUE)){
            return true
        }
        return false
    }
}
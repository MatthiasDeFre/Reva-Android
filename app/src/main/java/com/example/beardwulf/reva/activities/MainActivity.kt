package com.example.beardwulf.reva.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.activities.registreren.Registreren
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener {
            login()
        }
    }

    fun login() {
        val tekst: String
        if(txtInput.text.toString().equals("1234")) {
            tekst = getString(R.string.welkom)
            Toast.makeText(this@MainActivity, tekst , Toast.LENGTH_SHORT).show()
            val intent: Intent
            intent = Intent(this, Registreren::class.java)
            startActivity(intent)
        }
        else {
            tekst = "Code niet correct"
            Toast.makeText(this@MainActivity, tekst , Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
    }

}

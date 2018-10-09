package com.example.beardwulf.reva

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var txtCode: TextView
    //   private val txtCode = findViewById<TextView>(R.id.input) as TextView
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin = findViewById<Button>(R.id.btnLogin)
        txtCode = findViewById<TextView>(R.id.input)

        btnLogin.setOnClickListener {
            login()
        }
    }

    fun login() {
        val tekst: String
        if(txtCode.text.toString().equals("1234")) {
            tekst = "Welcome, Jens"
            Toast.makeText(this@MainActivity, tekst , Toast.LENGTH_SHORT).show()
            val intent: Intent
            intent = Intent(this, GroupPhoto::class.java)
            startActivity(intent)
        }
        else {
            tekst = "Code niet correct"
            Toast.makeText(this@MainActivity, tekst , Toast.LENGTH_SHORT).show()
        }
    }
}

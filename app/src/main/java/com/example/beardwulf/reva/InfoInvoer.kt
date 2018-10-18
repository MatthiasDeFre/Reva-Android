package com.example.beardwulf.reva

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class InfoInvoer : AppCompatActivity() {

    lateinit var nextButton: Button;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_invoer)


        nextButton = findViewById(R.id.cmdNext);

        nextButton.setOnClickListener {
            intent = Intent(this, CategoryPreferences::class.java)
            startActivity(intent)
        }
    }
}

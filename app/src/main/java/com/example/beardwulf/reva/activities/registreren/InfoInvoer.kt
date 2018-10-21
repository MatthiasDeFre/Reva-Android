package com.example.beardwulf.reva.activities.registreren

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.activities.VoorkeurCategorieen
import kotlinx.android.synthetic.main.activity_info_invoer.*

class InfoInvoer : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_invoer)

        cmdNext.setOnClickListener {
            intent = Intent(this, VoorkeurCategorieen::class.java)
            startActivity(intent)
        }
    }
}

package com.example.beardwulf.reva.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.activities.MainActivity
import com.example.beardwulf.reva.activities.VoorkeurCategorieen
import kotlinx.android.synthetic.main.fragment_registreer_groep.*

class RegistreerGroep : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        cmdNaarCategorie.setOnClickListener {
            var intent = Intent(this.context, VoorkeurCategorieen::class.java)
            startActivity(intent)
        }

        return inflater.inflate(R.layout.fragment_registreer_groep, container, false)
    }

    companion object {

        fun newInstance(): RegistreerGroep {
            return RegistreerGroep()
        }
    }
}

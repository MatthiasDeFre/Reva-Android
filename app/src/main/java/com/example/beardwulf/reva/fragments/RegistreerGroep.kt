package com.example.beardwulf.reva.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.beardwulf.reva.R

class RegistreerGroep : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_registreer_groep, container, false)
    }

    companion object {

        fun newInstance(): RegistreerGroep {
            return RegistreerGroep()
        }
    }
}

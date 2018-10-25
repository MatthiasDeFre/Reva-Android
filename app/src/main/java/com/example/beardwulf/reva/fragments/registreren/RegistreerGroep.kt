package com.example.beardwulf.reva.fragments.registreren

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.activities.VoorkeurCategorieen
import kotlinx.android.synthetic.main.fragment_registreer_groep.*
import org.jetbrains.anko.find

class RegistreerGroep : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.fragment_registreer_groep, container, false)

        view.find<Button>(R.id.cmdNaarCategorie).setOnClickListener {
            var intent = Intent(activity, VoorkeurCategorieen::class.java)
            startActivity(intent)
        }

        return view
    }

    override fun onResume() {
        super.onResume()

    }

    companion object {

        fun newInstance(): RegistreerGroep {
            return RegistreerGroep()
        }
    }
}
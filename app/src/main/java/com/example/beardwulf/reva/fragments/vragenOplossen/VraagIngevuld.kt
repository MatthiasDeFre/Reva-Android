package com.example.beardwulf.reva.fragments.vragenOplossen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.fragments.EindeSpel
import org.jetbrains.anko.support.v4.find

//Fragment ter bevestiging van een antwoord invullen en versturen
class VraagIngevuld : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_vraag_ingevuld, container, false)

        view.findViewById<Button>(R.id.btnOke).setOnClickListener {
            val fragmentTransaction = fragmentManager?.beginTransaction()
            if (true) {
                fragmentTransaction?.replace(R.id.fragment, VraagInvullen.newInstance())
            } else {
                fragmentTransaction?.replace(R.id.fragment, EindeSpel.newInstance())
            }
            fragmentTransaction?.commit()
        }
        return view
    }

    companion object {
        fun newInstance(): VraagIngevuld {
            return VraagIngevuld()
        }
    }
}

package com.example.beardwulf.reva.fragments.registreren

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.activities.registreren.Registreren
import kotlinx.android.synthetic.main.fragment_registreer_groep.*

class RegistreerGroep : Fragment() {
    /**
     * Registreer de gegevens van de groep
     * groepsnaam, groepsleden
     */
    lateinit var parent: Registreren


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_registreer_groep, container, false)

        parent = (activity as Registreren)

        return view
    }

    override fun onResume() {
        super.onResume()
        cmdNaarCategorie.setOnClickListener {
            parent.setFragment(RegisterCategories.newInstance())
        }
    }

    companion object {
        fun newInstance(): RegistreerGroep {
            return RegistreerGroep()
        }
    }
}

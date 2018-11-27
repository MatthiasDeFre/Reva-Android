package com.example.beardwulf.reva.fragments.registreren

import android.content.Intent
import com.example.beardwulf.reva.extensions.InputRegex
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.activities.registreren.Registreren
import com.example.beardwulf.reva.interfaces.RegisterCallbacks
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_registreer_groep.*

class RegistreerGroep : Fragment() {
    /**
     * Registreer de gegevens van de groep
     * groepsnaam, groepsleden
     */
    lateinit var parent: RegisterCallbacks


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_registreer_groep, container, false)

        parent = (activity as RegisterCallbacks)

        return view
    }

    override fun onResume() {
        super.onResume()
            cmdNaarCategorie.setOnClickListener {
                if (InputRegex.controleerLettersCijfers(txtGroepsnaam.text.toString())) {
                        parent.setNameAndDescription(name = txtGroepsnaam.text.toString(), description = txtGroepsLeden.text.toString())
                        //parent.setFragment(RegisterCategories.newInstance())
                } else {
                    txtGroepsnaam.setError("groepsnaam: enkel letters en cijfers aub");
                }
            }
    }

    companion object {
        fun newInstance(): RegistreerGroep {
            return RegistreerGroep()
        }
    }
}

package com.example.beardwulf.reva.fragments.registreren

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.beardwulf.reva.Endpoint
import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.RetrofitClientInstance
import com.example.beardwulf.reva.activities.VoorkeurCategorieen
import com.example.beardwulf.reva.domain.Group
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_registreer_groep.*
import org.jetbrains.anko.find
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistreerGroep : Fragment() {
    /**
     * Registreer de gegevens van de groep
     * groepsnaam, groepsleden
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_registreer_groep, container, false)
        return view
    }

    override fun onResume() {
        super.onResume()
        cmdNaarCategorie.setOnClickListener {
            var intent = Intent(activity, VoorkeurCategorieen::class.java)
            startActivity(intent)
        }
    }

    companion object {
        fun newInstance(): RegistreerGroep {
            return RegistreerGroep()
        }
    }
}

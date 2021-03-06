package com.example.beardwulf.reva.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.beardwulf.reva.R

/**
 * Fragment die toont dat de vragenlijst afgerond is
 */
class EindeSpel : Fragment() {

    /**
     * Deze methode wordt gebruikt om informatie over de staat van uw activiteit op te slaan en te herstellen.
     * In gevallen zoals oriĆ«ntatieveranderingen, de app afsluiten of een ander scenario dat leidt tot het opnieuw oproepen van onCreate(),
     * kan de savedInstanceState bundel gebruikt worden om de vorige toestandsinformatie opnieuw te laden.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /**
     * onCreateView wordt opgeroepen om de lay-out van het fragment "op te blazen"(inflate),
     * d.w.z. dat de grafische initialisatie meestal hier plaatsvindt.
     * Het wordt altijd aangeroepen na de onCreate methode.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_einde_spel, container, false)
    }

    /**
     * Dit object is een singleton-object dat met de naam van de klasse genoemd kan worden. Elke methode in dit object kan gebruikt worden in andere klassen.
     */
    companion object {
        fun newInstance(): EindeSpel {
            return EindeSpel()
        }
    }
}

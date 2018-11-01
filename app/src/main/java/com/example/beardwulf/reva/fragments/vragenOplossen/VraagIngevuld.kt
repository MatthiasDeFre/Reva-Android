package com.example.beardwulf.reva.fragments.vragenOplossen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.activities.vragenOplossen.VragenOplossen
import com.example.beardwulf.reva.fragments.EindeSpel
import org.jetbrains.anko.find

/**
 * Fragment ter bevestiging van een antwoord invullen en versturen
 */
class VraagIngevuld : Fragment() {

    lateinit var parent: VragenOplossen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parent = (activity as VragenOplossen)
    }

    /**
     * Indien alle vragen zijn inguvld, open het eindscherm fragment. Anders toon de volgende vraag fragment.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_vraag_ingevuld, container, false)

        view.findViewById<Button>(R.id.btnOke).setOnClickListener {
            if (parent.questionNr != parent.questions?.size) {
                parent.setFragment(Kaart.newInstance(), R.id.fragment)
                parent.removeFragment(this)
            } else {
                parent.setFragment(EindeSpel.newInstance(), R.id.fragment2)
            }
        }
        return view
    }

    companion object {
        fun newInstance(): VraagIngevuld {
            return VraagIngevuld()
        }
    }
}

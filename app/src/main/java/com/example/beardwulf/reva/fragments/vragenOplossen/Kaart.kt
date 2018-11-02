package com.example.beardwulf.reva.fragments.vragenOplossen

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.activities.vragenOplossen.VragenOplossen
import kotlinx.android.synthetic.main.fragment_kaart.*
import kotlinx.android.synthetic.main.fragment_vraag_invullen.*
import org.jetbrains.anko.find

class Kaart : Fragment() {

    lateinit var parent: VragenOplossen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parent = (activity as VragenOplossen)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_kaart, container, false)

        //view.find<Button>(R.id.btnVraag)
        btnVraag.setOnClickListener {
            parent.setFragment(VraagInvullen.newInstance(), R.id.fragment)
        }

        return view
    }

    companion object {
        fun newInstance(): Kaart {
            return Kaart()
        }
    }
}

package com.example.beardwulf.reva.fragments.vragenOplossen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import com.example.beardwulf.reva.R
import org.jetbrains.anko.support.v4.find

class VraagIngevuld : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view?.findViewById<Button>(R.id.btnOke)?.setOnClickListener {
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment, VraagInvullen.newInstance())
            //fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }
        return inflater.inflate(R.layout.fragment_vraag_ingevuld, container, false)
    }

    companion object {
        fun newInstance(): VraagIngevuld {
            return VraagIngevuld()
        }
    }
}

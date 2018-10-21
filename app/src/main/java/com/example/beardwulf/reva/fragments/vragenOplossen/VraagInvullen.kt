package com.example.beardwulf.reva.fragments.vragenOplossen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import com.example.beardwulf.reva.R

class VraagInvullen : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = (inflater.inflate(R.layout.fragment_vraag_invullen, container, false))
        v.findViewById<Button>(R.id.btnVulIn).setOnClickListener {

        }

        return v
    }

    companion object {
        fun newInstance(): VraagInvullen {
            return VraagInvullen()
        }
}}

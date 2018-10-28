package com.example.beardwulf.reva.fragments.vragenOplossen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.activities.vragenOplossen.VragenOplossen
import kotlinx.android.synthetic.main.fragment_vraag_invullen.*
import org.jetbrains.anko.find


/**
 * Fragment voor het tonen van een vraag en inlezen van een antwoord
 */
class VraagInvullen : Fragment() {

    lateinit var parent: VragenOplossen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parent = (activity as VragenOplossen)
    }

    /**
     * Toont de vraag en een veld om een antwoord in te vullen.
     * Indien er een antwoord wordt inguvuld, opent het bevestigings fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = (inflater.inflate(R.layout.fragment_vraag_invullen, container, false))

        view.find<Button>(R.id.btnVulIn).setOnClickListener {
            if (txtInput.text.toString().isNotEmpty()) {
                parent.setFragment(VraagIngevuld.newInstance())
            } else {
                Toast.makeText(this.context, "Je moet een antwoord invullen!" , Toast.LENGTH_SHORT).show()
            }
        }

        view.find<TextView>(R.id.textView2).setText(parent.questions[parent.questionNr])
        view.find<TextView>(R.id.textView3).setText((parent.questionNr + 1).toString())

        parent.questionNr++

        return view
    }

    companion object {
        fun newInstance(): VraagInvullen {
            return VraagInvullen()
        }
    }
}

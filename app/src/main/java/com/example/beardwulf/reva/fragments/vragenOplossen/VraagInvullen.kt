package com.example.beardwulf.reva.fragments.vragenOplossen

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.beardwulf.reva.extensions.InputRegex
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.beardwulf.reva.Endpoint

import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.RetrofitClientInstance
import com.example.beardwulf.reva.activities.MainActivity
import com.example.beardwulf.reva.activities.vragenOplossen.VragenOplossen
import com.example.beardwulf.reva.domain.Exhibitor
import com.example.beardwulf.reva.domain.Group
import kotlinx.android.synthetic.main.fragment_vraag_invullen.*
import org.jetbrains.anko.find
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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

        if (MainActivity.group.code == null) {
            view.find<TextView>(R.id.textView2).setText(parent.questions[parent.questionNr])
        } else {
            view.find<TextView>(R.id.textView2).setText(parent.exhibitor.question!!.body)
        }
        view.find<TextView>(R.id.textView3).setText((parent.questionNr + 1).toString())
        parent.questionNr++

        return view
    }

    override fun onResume() {
        super.onResume()
        btnVulIn.setOnClickListener {
            if (InputRegex.controleerLettersCijfers(txtInput.text.toString())) {
/*                val service = RetrofitClientInstance().getRetrofitInstance()!!.create(Endpoint::class.java!!)
                val call = service.getExhibitor(MainActivity.group._id!!)
                call.enqueue(object : Callback<Exhibitor> {
                    override fun onResponse(call: Call<Exhibitor>, response: Response<Exhibitor>) {
                        Log.d("lolz", response.code().toString())
                    }

                    override fun onFailure(call: Call<Exhibitor>, t: Throwable) {
                        Log.d("lolzzzz", t.message)
                    }
                })*/

                parent.setFragment(Kaart.newInstance(), R.id.fragment)
                parent.unfocusMap()

/*                btnVulIn.isEnabled = false
                btnVulIn.alpha = 0.4F

                txtInput.isEnabled = false;
                txtInput.alpha = 0.4F*/

                val vraagIngevuld = VraagIngevuld.newInstance()
                parent.setFragment(vraagIngevuld, R.id.fragment2)
                parent.vraagIngevuld = vraagIngevuld
            } else {
                Toast.makeText(this.context, "Enkel tekst invullen aub", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        fun newInstance(): VraagInvullen {
            return VraagInvullen()
        }
    }
}

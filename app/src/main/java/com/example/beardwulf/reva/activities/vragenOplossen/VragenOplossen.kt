package com.example.beardwulf.reva.activities.vragenOplossen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.fragments.vragenOplossen.VraagIngevuld
import com.example.beardwulf.reva.fragments.vragenOplossen.VraagInvullen
import kotlinx.android.synthetic.main.activity_vragen_oplossen.*

/**
 * Activity die het tonen van alle vragen en inlezen van alle antwoorden verzorgt
 */

class VragenOplossen : AppCompatActivity() {

    var questionNr = 0
    var questions = arrayOf("TestVraag1?", "TestVraag2?")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vragen_oplossen)

        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment, VraagInvullen.newInstance())
        fragmentTransaction.commit()
    }

    companion object {
        fun newInstance(): VragenOplossen {
            return newInstance()
        }
    }
}

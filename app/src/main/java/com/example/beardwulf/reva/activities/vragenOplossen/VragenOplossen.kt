package com.example.beardwulf.reva.activities.vragenOplossen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.fragments.vragenOplossen.VraagIngevuld
import com.example.beardwulf.reva.fragments.vragenOplossen.VraagInvullen
import kotlinx.android.synthetic.main.activity_vragen_oplossen.*

/**
 * Activity die het tonen van alle vragen en inlezen van alle antwoorden verzorgt
 */
class VragenOplossen : AppCompatActivity() {

    var questionNr = 0
    var questions = arrayOf("Hoeveel spelers zijn er op het veld tijdens een wedstrijd rolstoelbasketbal? (Beide ploegen samen opgeteld)", "Hoeveel kost de nieuwste kruk van VIGO?")

    /**
     * Opent de fragment voor het tonen en beantwoorden van een vraag
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vragen_oplossen)

        setFragment(VraagInvullen.newInstance())
    }

    fun setFragment(fragment: Fragment) {
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment, fragment)
        fragmentTransaction.commit()
    }

    companion object {
        fun newInstance(): VragenOplossen {
            return newInstance()
        }
    }
}

package com.example.beardwulf.reva.activities.vragenOplossen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.domain.Category
import com.example.beardwulf.reva.domain.Exhibitor
import com.example.beardwulf.reva.fragments.EindeSpel
import com.example.beardwulf.reva.fragments.vragenOplossen.Kaart
import com.example.beardwulf.reva.fragments.vragenOplossen.VraagIngevuld
import com.example.beardwulf.reva.fragments.vragenOplossen.VraagInvullen
import kotlinx.android.synthetic.main.activity_vragen_oplossen.*

/**
 * Activity die het tonen van alle vragen en inlezen van alle antwoorden verzorgt
 */
class VragenOplossen : AppCompatActivity() {

    var questionNr = 0
    var questions = arrayOf(
            "Hoeveel kost de nieuwste kruk van VIGO?",
            "Hoeveel spelers zijn er op het veld tijdens een wedstrijd rolstoelbasketbal? (Beide ploegen samen opgeteld)"
    )

    lateinit var kaart: Kaart
    lateinit var vraagInvullen: VraagInvullen
    lateinit var vraagIngevuld: VraagIngevuld
    lateinit var eindeSpel: EindeSpel
    lateinit var exhibitors: ArrayList<Exhibitor>

    /**
     * Opent de fragment voor het tonen en beantwoorden van een vraag
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vragen_oplossen)
        setFragment(Kaart.newInstance(), R.id.fragment)

        makeExhibitors()
    }

    private fun makeExhibitors() {
        exhibitors= ArrayList(2)
        var exhibitor2 = Exhibitor("Test", 0, Category("Rolstoelen"), Pair(8,5))
        var exhibitor1 = Exhibitor("Test", 0, Category("Rolstoelen"), Pair(10, 3))
        var exhibitor0 = Exhibitor("Test", 0, Category("Rolstoelen"), Pair(5, 5))
        exhibitors.add(exhibitor2)
        exhibitors.add(exhibitor1)
        exhibitors.add(exhibitor0)

    }

    fun setFragment(fragment: Fragment, int: Int) {
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(int, fragment)
        fragmentTransaction.commit()
    }

    fun removeFragment(fragment: Fragment) {
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.remove(fragment)
        fragmentTransaction.commit()
    }

<<<<<<< HEAD
    fun currentExhibitor(): Exhibitor {
        return exhibitors[questionNr]
=======
    fun unfocusMap() {
       fragment.alpha = 0.1F

    }

    fun focusMap(){
        fragment.alpha = 1.0F
>>>>>>> 7970aa4c140d28affdf6cd993e9e5dd569380cfa
    }

    companion object {
        fun newInstance(): VragenOplossen {
            return newInstance()
        }
    }
}

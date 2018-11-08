package com.example.beardwulf.reva.activities.vragenOplossen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.widget.TextView
import com.example.beardwulf.reva.Endpoint
import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.RetrofitClientInstance
import com.example.beardwulf.reva.activities.MainActivity
import com.example.beardwulf.reva.domain.Exhibitor
import com.example.beardwulf.reva.fragments.EindeSpel
import com.example.beardwulf.reva.fragments.vragenOplossen.Kaart
import com.example.beardwulf.reva.fragments.vragenOplossen.VraagIngevuld
import com.example.beardwulf.reva.fragments.vragenOplossen.VraagInvullen
import kotlinx.android.synthetic.main.activity_vragen_oplossen.*
import org.jetbrains.anko.find
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Activity die het tonen van alle vragen en inlezen van alle antwoorden verzorgt
 */
class VragenOplossen : AppCompatActivity() {

    var questionNr = 0
    var questions = arrayOf(
            "Hoeveel spelers zijn er op het veld tijdens een wedstrijd rolstoelbasketbal? (Beide ploegen samen opgeteld)",
            "Hoeveel kost de nieuwste kruk van VIGO?"
    )

    lateinit var kaart: Kaart
    lateinit var vraagInvullen: VraagInvullen
    lateinit var vraagIngevuld: VraagIngevuld
    lateinit var eindeSpel: EindeSpel

    lateinit var exhibitor: Exhibitor

    /**
     * Opent de fragment voor het tonen en beantwoorden van een vraag
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val service = RetrofitClientInstance().getRetrofitInstance()!!.create(Endpoint::class.java!!)
        val call = service.getExhibitor(MainActivity.group._id!!)
        call.enqueue(object : Callback<Exhibitor> {
            override fun onResponse(call: Call<Exhibitor>, response: Response<Exhibitor>) {
                exhibitor = Exhibitor(response.body()!!._id, response.body()!!.name, response.body()!!.visits, response.body()!!.category, response.body()!!.coordinates, response.body()!!.question)

                setContentView(R.layout.activity_vragen_oplossen)
                setFragment(Kaart.newInstance(), R.id.fragment)
            }

            override fun onFailure(call: Call<Exhibitor>, t: Throwable) {
                Log.d("Error", t.message)
            }
        })
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

    companion object {
        fun newInstance(): VragenOplossen {
            return newInstance()
        }
    }
}

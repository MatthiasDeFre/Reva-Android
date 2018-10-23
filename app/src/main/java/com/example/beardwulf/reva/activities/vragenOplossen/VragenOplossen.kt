package com.example.beardwulf.reva.activities.vragenOplossen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.fragments.vragenOplossen.VraagIngevuld
import com.example.beardwulf.reva.fragments.vragenOplossen.VraagInvullen
import kotlinx.android.synthetic.main.activity_vragen_oplossen.*

class VragenOplossen : AppCompatActivity() {

/*    var vraagInvullen = VraagInvullen.newInstance()
    var vraagIngevuld = VraagIngevuld.newInstance()*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vragen_oplossen)
    }

    companion object {
        fun newInstance(): VragenOplossen {
            return newInstance()
        }
    }
}

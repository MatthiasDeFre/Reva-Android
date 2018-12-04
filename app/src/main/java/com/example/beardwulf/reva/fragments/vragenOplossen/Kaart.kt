package com.example.beardwulf.reva.fragments.vragenOplossen

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast

import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.activities.registreren.Registreren
import com.example.beardwulf.reva.activities.vragenOplossen.VragenOplossen
import com.example.beardwulf.reva.domain.Category
import com.example.beardwulf.reva.domain.Exhibitor
import com.example.beardwulf.reva.interfaces.QuestionCallbacks
import kotlinx.android.synthetic.main.fragment_kaart.*
import kotlinx.android.synthetic.main.fragment_vraag_invullen.*
import org.jetbrains.anko.find
import org.jetbrains.anko.image
import kotlin.math.exp

class Kaart : Fragment() {

    lateinit var parent: QuestionCallbacks
    val beaconSize = 100


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parent = (activity as QuestionCallbacks)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_kaart, container, false)
        return view
    }

    override fun onResume() {
        super.onResume()
        //showNextExhibitor(parent.exhibitor)
        showNextExhibitor((parent.currentExhibitor))
        btnVraag.setOnClickListener {
           parent.setNextQuestion()
        }
        if (parent.currentExhibitor.question.counter == parent.maxQuestion) {
            btnVraag.isEnabled = false
        }

        if (VragenOplossen.getSizeName(activity!!.applicationContext) === "large" || VragenOplossen.getSizeName(activity!!.applicationContext) === "xlarge") {
            btnVraag.visibility = View.INVISIBLE
        }

    }

    fun showNextExhibitor(exhibitor: Exhibitor) {

        txtExhibitorName.setText((exhibitor.question.counter).toString() + ". " + exhibitor.name)
        txtCategoryName.setText(exhibitor.category)

       // var beacon = ImageView()
        if(KaartConstraintLayout.childCount ==2) {
            KaartConstraintLayout.removeViewAt(1)
        }
        var beacon = WebView(context)
        //var xCo = exhibitor.coordinates!!.first
        //var yCo = exhibitor.coordinates!!.second
        var xCo = 500
        var yCo = 750
        //var xPosition = (imageKaart.drawable.intrinsicWidth  / 10 * xCo).toFloat()
        var xPosition = (imageKaart.layoutParams.width/20*xCo).toFloat()
        var yPosition = (imageKaart.drawable.intrinsicHeight/8 * yCo).toFloat()

        //var coords = IntArray(2)
        //imageKaart.getLocationInWindow(coords)

        System.out.println(imageKaart.layoutParams.width)

        System.out.println("" + xPosition + " - " + yPosition)
        //beacon.setImageDrawable(resources.getDrawable(R.drawable.beaconbak))
        beacon.loadUrl("file:///android_asset/beacon.gif")
        beacon.settings.loadWithOverviewMode=true
        beacon.settings.useWideViewPort = true
        beacon.setBackgroundColor(Color.TRANSPARENT)

        beacon.x = xPosition
        beacon.y = yPosition
        KaartConstraintLayout.addView(beacon)
        beacon.layoutParams.width = beaconSize
        beacon.layoutParams.height = beaconSize
    }

    companion object {
        fun newInstance(): Kaart {
            return Kaart()
        }
    }
}

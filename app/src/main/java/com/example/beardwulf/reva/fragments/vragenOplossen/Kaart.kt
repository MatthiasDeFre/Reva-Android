package com.example.beardwulf.reva.fragments.vragenOplossen

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.graphics.PointF
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.davemorrissey.labs.subscaleview.ImageSource

import com.davemorrissey.labs.subscaleview.*
import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.activities.registreren.Registreren
import com.example.beardwulf.reva.activities.vragenOplossen.VragenOplossen
import com.example.beardwulf.reva.domain.Category
import com.example.beardwulf.reva.domain.Exhibitor
import com.example.beardwulf.reva.domain.ExhibitorViewModel
import com.example.beardwulf.reva.interfaces.QuestionCallbacks
import com.example.beardwulf.reva.views.PinView
import kotlinx.android.synthetic.main.fragment_kaart.*
import kotlinx.android.synthetic.main.fragment_vraag_invullen.*
import org.jetbrains.anko.find
import org.jetbrains.anko.image
import kotlin.math.exp

class Kaart : Fragment() {

    lateinit var parent: MapCallbacks
    val beaconSize = 100
    lateinit var currentExhibitor: Exhibitor
    lateinit var currentExhibitorViewModel: ExhibitorViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parent = (activity as MapCallbacks)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_kaart, container, false)
        Log.d("MAP", "MAP START")
        currentExhibitorViewModel = ViewModelProviders.of(activity!!).get( ExhibitorViewModel::class.java);
        currentExhibitor = currentExhibitorViewModel.currentExhibitor
        Log.d("MAP", currentExhibitor.name)
        return view
    }

    override fun onResume() {
        super.onResume()
        //showNextExhibitor(parent.exhibitor)
        showNextExhibitor(currentExhibitor)
        btnVraag.setOnClickListener {
           parent.goToNextQuestion()
        }
        if (currentExhibitor.question.counter == parent.maxQuestion) {
            btnVraag.isEnabled = false
        }

        if (VragenOplossen.getSizeName(activity!!.applicationContext) === "large" || VragenOplossen.getSizeName(activity!!.applicationContext) === "xlarge") {
            if (VragenOplossen.determineOrientation(activity!!.applicationContext) == true) {
                btnVraag.visibility = View.INVISIBLE
            }
        }
        }

    fun showNextExhibitor(exhibitor: Exhibitor) {

        txtExhibitorName.setText((exhibitor.question.counter).toString() + ". " + exhibitor.name)
        txtCategoryName.setText(exhibitor.category)

       // var beacon = ImageView()
        if(KaartConstraintLayout.childCount ==2) {
            KaartConstraintLayout.removeViewAt(1)
        }

        var xCo = exhibitor.coordinates.xCo
        var yCo = exhibitor.coordinates.yCo
        //var xPosition = (imageKaart.drawable.intrinsicWidth  / 10 * xCo).toFloat()
      //  var xPosition = (imageKaart.layoutParams.width/20*xCo).toFloat()
      //  var yPosition = (imageKaart.drawable.intrinsicHeight/8 * yCo).toFloat()

        imageKaart.setImage(ImageSource.asset("grondplan.jpg"))
        imageKaart.setPin(PointF(xCo.toFloat(), yCo.toFloat()))
    }
    interface MapCallbacks {
        fun goToNextQuestion()
        var maxQuestion : Int
    }
    companion object {
        fun newInstance(): Kaart {
            return Kaart()
        }
    }
}

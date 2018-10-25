package com.example.beardwulf.reva.activities.registreren

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.annotation.NonNull
import android.widget.Button
import android.widget.Toast
import com.example.beardwulf.reva.ImageHelper
import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.activities.VoorkeurCategorieen
import com.example.beardwulf.reva.fragments.registreren.RegisterPhoto
import com.example.beardwulf.reva.fragments.registreren.RegistreerGroep
import com.example.beardwulf.reva.fragments.vragenOplossen.VraagInvullen
import kotlinx.android.synthetic.main.fragment_register_photo.*
import kotlinx.android.synthetic.main.fragment_registreer_groep.*

class Registreren : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registreren)


        var conf = Bitmap.Config.ARGB_8888
        photo = Bitmap.createBitmap(306, 306, conf)


        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.registreerlayout, RegisterPhoto.newInstance())
        fragmentTransaction.commit()
    }


    companion object {

        /**
         * Variabele dat wordt gebruikt om de imageview voor de groepsfoto op te vullen
         *
         */
        lateinit var photo: Bitmap
    }


}

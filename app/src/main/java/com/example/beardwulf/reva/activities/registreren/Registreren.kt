package com.example.beardwulf.reva.activities.registreren

import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.fragments.registreren.RegisterPhoto

class Registreren : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registreren)

        var conf = Bitmap.Config.ARGB_8888
        photo = Bitmap.createBitmap(306, 306, conf)

        setFragment(RegisterPhoto.newInstance())
    }

    fun setFragment(fragment: Fragment) {
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.registreerlayout, fragment)
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

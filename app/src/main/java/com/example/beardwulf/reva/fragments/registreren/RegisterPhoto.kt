package com.example.beardwulf.reva.fragments.registreren

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.beardwulf.reva.ImageHelper

import com.example.beardwulf.reva.R
import kotlinx.android.synthetic.main.fragment_register_photo.*

class RegisterPhoto : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_photo, container, false)
    }



    companion object {
        fun newInstance(): RegisterPhoto {
            return RegisterPhoto()
        }
    }
}

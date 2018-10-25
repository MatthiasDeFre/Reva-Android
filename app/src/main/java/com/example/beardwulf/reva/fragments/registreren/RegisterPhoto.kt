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
import android.widget.ImageView
import android.widget.Toast
import com.example.beardwulf.reva.ImageHelper
import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.activities.registreren.Registreren
import kotlinx.android.synthetic.main.fragment_register_photo.*
import org.jetbrains.anko.find

class RegisterPhoto : Fragment() {

    private val MY_CAMERA_PERMISSION_CODE = 100;
    private val CAMERA_REQUEST = 1888;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_register_photo, container, false)
        view.find<ImageView>(R.id.photoViewer).setImageBitmap(Registreren.photo)
        //photoViewer.setImageBitmap(Registreren.photo)
        return view
    }


    override fun onResume() {
        super.onResume()

        cmdNeemFoto.setOnClickListener {
            //txtStap2.text="Dit is nu veranderd"
            var cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);


            //txtStap2.text="Dit is nog eens veranderd"
        }
        cmdVolgende.setOnClickListener {
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.registreerlayout, RegistreerGroep.newInstance())
            fragmentTransaction?.commit()

        }
        photoViewer.setImageBitmap(Registreren.photo)
    }


    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>, @NonNull grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode.equals(MY_CAMERA_PERMISSION_CODE)) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(activity, "camera permission granted", Toast.LENGTH_LONG).show();
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(activity, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            var foto = data?.extras?.get("data") as Bitmap
            foto = ImageHelper.getRoundedCornerBitmap(foto, foto.width / 2)

            Registreren.photo = foto

            cmdVolgende.setEnabled(true);
        }
    }


    companion object {
        fun newInstance(): RegisterPhoto {
            return RegisterPhoto()
        }
    }
}

package com.example.beardwulf.reva.fragments.registreren

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.beardwulf.reva.ImageHelper
import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.activities.registreren.Registreren
import kotlinx.android.synthetic.main.fragment_register_photo.*
import org.jetbrains.anko.find
import android.os.StrictMode
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import com.example.beardwulf.reva.R.id.imageView
import android.R.attr.data
import android.content.ContentResolver
import java.net.SocketOption
import android.content.ContentValues
import android.R.attr.thumbnail


class RegisterPhoto : Fragment() {

    //private val MY_CAMERA_PERMISSION_CODE = 100;
    private val CAMERA_REQUEST = 1888;
    var mCameraFileName = ""
    lateinit var image: Uri
    lateinit var values: ContentValues

    lateinit var parent: Registreren

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_register_photo, container, false)
        //view.find<ImageView>(R.id.photoViewer)
//        photoViewer.setImageBitmap(Registreren.photo)

        parent = (activity as Registreren)

        return view
    }

    /**
     * Aanmaken van de clicklisteners van de knoppen
     * Als laatste wordt de imageview photoViewer opgevuld met het statische variable photo van de Regesteren activity
     */
    override fun onResume() {
        super.onResume()

        photoViewer.setImageBitmap(Registreren.photo)
        //photoViewer.setImageURI(Registreren.photoUri)
        cmdNeemFoto.setOnClickListener {
            var cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);

        }
        cmdVolgende.setOnClickListener {
            parent.setFragment(RegistreerGroep.newInstance())
        }
        photoViewer.setImageBitmap(Registreren.photo)
        //photoViewer.setImageURI(Registreren.photoUri)
    }

<<<<<<< HEAD

=======
>>>>>>> 7970aa4c140d28affdf6cd993e9e5dd569380cfa
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            /**
             * Bitmap uitpakken en in het statische fotovariabele steken,
             * hierna wordt de knop om naar het volgende scherm te gaan aangezet
             */
            var foto = data?.extras?.get("data") as Bitmap
            foto = ImageHelper.getRoundedCornerBitmap(foto, foto.width / 2)
            Registreren.photo = foto
            cmdVolgende.setEnabled(true);
            cmdNeemFoto.setText(getString(R.string.fotowijzigen))
        }
    }

    companion object {
        fun newInstance(): RegisterPhoto {
            return RegisterPhoto()
        }
    }
}

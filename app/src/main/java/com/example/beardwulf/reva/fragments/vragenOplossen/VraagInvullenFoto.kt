package com.example.beardwulf.reva.fragments.vragenOplossen

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v4.content.FileProvider
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.beardwulf.reva.Endpoint
import com.example.beardwulf.reva.ImageHelper

import com.example.beardwulf.reva.R
import com.example.beardwulf.reva.RetrofitClientInstance
import com.example.beardwulf.reva.activities.MainActivity
import com.example.beardwulf.reva.activities.vragenOplossen.VragenOplossen
import com.example.beardwulf.reva.domain.Exhibitor
import com.example.beardwulf.reva.domain.Group
import com.example.beardwulf.reva.interfaces.QuestionCallbacks
import kotlinx.android.synthetic.main.fragment_vraag_invullen_foto.*
import org.jetbrains.anko.find
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


/**
 * Fragment voor het tonen van een vraag en inlezen van een antwoord
 */
class VraagInvullenFoto : Fragment() {

    lateinit var parent: QuestionCallbacks
    lateinit var photo : Bitmap
    lateinit var photoUri : Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parent = (activity as QuestionCallbacks)
    }

    /**
     * Toont de vraag en een veld om een antwoord in te vullen.
     * Indien er een antwoord wordt inguvuld, opent het bevestigings fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = (inflater.inflate(R.layout.fragment_vraag_invullen_foto, container, false))
        var conf = Bitmap.Config.ARGB_8888
        photo = Bitmap.createBitmap(306, 306, conf)
        return view
    }

    override fun onResume() {
        super.onResume()
        textView2.text = parent.currentExhibitor.question.body
        textView3.text = (parent.currentExhibitor.question.counter).toString()
        cmdNeemFoto.setOnClickListener {
            dispatchTakePictureIntent()
            /* var cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
             val outFile = File(activity!!.cacheDir,"test.png")
             outFile.createNewFile()
             var uri = Uri.fromFile(outFile)
             cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
             cameraIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
             startActivityForResult(cameraIntent, CAMERA_REQUEST);*/

        }
        btnVulIn.setOnClickListener {
            // parent.setFragment(RegistreerGroep.newInstance())
            parent.setAnswer(photo = photo);
        }
        photoViewer.setImageBitmap(photo)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        print("test")
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == Activity.RESULT_OK) {
            /**
             * Bitmap uitpakken en in het statische fotovariabele steken,
             * hierna wordt de knop om naar het volgende scherm te gaan aangezet
             */
            //var foto = data?.extras?.get("data") as Bitmap
            var foto : Bitmap

            foto = parent.getPhoto(photoUri)
            foto = ImageHelper.getRoundedCornerBitmap(foto, foto.width / 2)
            photo = foto
            print(foto)
            btnVulIn.setEnabled(true);
            cmdNeemFoto.setText(getString(R.string.fotowijzigen))
        }
    }
    var mCurrentPhotoPath: String = ""

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = activity!!.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
                "JPEG_${timeStamp}_", /* prefix */
                ".jpg", /* suffix */
                storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            mCurrentPhotoPath = absolutePath
        }
    }
    val REQUEST_TAKE_PHOTO = 1
    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(activity!!.packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    // Error occurred while creating the File

                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                            activity!!,
                            "com.example.beardwulf.fileprovider",
                            it
                    )
                    this.photoUri = photoURI
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                }
            }
        }
    }
    companion object {
        fun newInstance(): VraagInvullenFoto {
            return VraagInvullenFoto()
        }
    }
}

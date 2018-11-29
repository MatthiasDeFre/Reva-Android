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
import android.arch.lifecycle.ViewModelProviders
import android.os.Environment
import android.support.v4.content.FileProvider
import android.util.Log
import com.example.beardwulf.reva.domain.PhotoViewModel
import com.example.beardwulf.reva.interfaces.RegisterCallbacks
import java.io.IOException


class RegisterPhoto : Fragment() {

    //private val MY_CAMERA_PERMISSION_CODE = 100;
    private lateinit  var photoViewModel : PhotoViewModel
    private val CAMERA_REQUEST = 1888;
    var mCameraFileName = ""
    lateinit var image: Uri
    lateinit var values: ContentValues

    lateinit var parent: RegisterPhotoCallbacks

    lateinit var photo : Bitmap
    lateinit var photoUri : Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_register_photo, container, false)
        //view.find<ImageView>(R.id.photoViewer)
//        photoViewer.setImageBitmap(Registreren.photo)
        var conf = Bitmap.Config.ARGB_8888
        photo = Bitmap.createBitmap(306, 306, conf)
        parent = (activity as RegisterPhotoCallbacks)
        photoViewModel = ViewModelProviders.of(this).get( PhotoViewModel::class.java)

        return view
    }

    /**
     * Aanmaken van de clicklisteners van de knoppen
     * Als laatste wordt de imageview photoViewer opgevuld met het statische variable photo van de Regesteren activity
     */
    override fun onResume() {
        super.onResume()

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
        cmdVolgende.setOnClickListener {
           // parent.setFragment(RegistreerGroep.newInstance())
            photoViewModel.photo = photo
            photoViewModel.photoUri = photoUri
            parent.goToGroupDetails()
           // parent.setPhoto(photo = photo, photoUri = photoUri);
        }
       photoViewer.setImageBitmap(photo)
        //photoViewer.setImageURI(photoUri)
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

            foto = MediaStore.Images.Media.getBitmap(activity!!.contentResolver, photoUri)
            foto = ImageHelper.getRoundedCornerBitmap(foto, foto.width / 2)
            photo = foto
            print(foto)
            cmdVolgende.setEnabled(true);
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
    interface RegisterPhotoCallbacks {
        fun goToGroupDetails()

    }
    companion object {
        fun newInstance(): RegisterPhoto {
            return RegisterPhoto()
        }
    }
}

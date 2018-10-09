package com.example.beardwulf.reva

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.annotation.NonNull
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_group_photo.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class GroupPhoto : AppCompatActivity() {

    private lateinit var imageView: ImageView;

    lateinit var photoButton: Button;
    lateinit var nextButton: Button;

    val MY_CAMERA_PERMISSION_CODE = 100;
    private final val CAMERA_REQUEST = 1888;
    val REQUEST_IMAGE_CAPTURE = 1
    private lateinit var mImageBitmap: Bitmap
    private lateinit var mCurrentPhotoPath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_photo)

        imageView = findViewById(R.id.photoView);
        photoButton = findViewById(R.id.cmdPhoto);
        nextButton = findViewById(R.id.cmdNext);


        nextButton.setOnClickListener {
            var cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>, @NonNull grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode.equals(MY_CAMERA_PERMISSION_CODE)) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            //var options = BitmapFactory.Options();
            //options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            //var photo = BitmapFactory.decodeFile(capturedImageUri.toString(), options)
            var photo: Bitmap
            photo = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(photo)
        }
    }


        fun arrangeButtons() {
            nextButton.visibility = View.VISIBLE

            val param = photoButton.layoutParams as LinearLayout.LayoutParams
            param.setMargins(28, 0, 28, 76)
            photoButton.layoutParams = param

            val layout = photoButton.layout
            photoButton.text = "Neem nieuwe foto"

        }
    }


/*

photoButton.setOnClickListener {
            var cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (cameraIntent.resolveActivity(packageManager) != null) {
                var photoFile: File? = null
                try {
                    photoFile = createImageFile()
                } catch (ex: IOException) {
                    //Toast.makeText(this, ex.message, Toast.LENGTH_SHORT).show();
                }

                if (photoFile != null) {
                    Toast.makeText(this, "Hier geraak ik", Toast.LENGTH_SHORT).show();
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                    startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
                }
                else {
                    //Toast.makeText(this, "Er ist iets misgegaan", Toast.LENGTH_SHORT).show();
                }
            }
            //startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }


fun createImageFile(): File {

        var mContext: Context = this.applicationContext


        var check = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (check == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Foto'tje maken", Toast.LENGTH_SHORT).show();
        } else {
            val x: Array<String> = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            ActivityCompat.requestPermissions(mContext as Activity, x, 1888)
            requestPermissions( x,1024);
            Toast.makeText(this, "Rip", Toast.LENGTH_SHORT).show();

        }

        val imageFileName = "JPEG_" + "_"
        val storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES)

        val image = File.createTempFile(
                imageFileName, // prefix
                ".jpg", // suffix
                storageDir      // directory
        )
        Toast.makeText(this, "Foto'tje maken", Toast.LENGTH_SHORT).show();
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.absolutePath
        return image
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            try {
                mImageBitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, Uri.parse(mCurrentPhotoPath))
                imageView.setImageBitmap(mImageBitmap)
            } catch (e: IOException) {
                Toast.makeText(this, "Er ist iets misgegaan", Toast.LENGTH_SHORT).show();
            }
        }
    }

 */
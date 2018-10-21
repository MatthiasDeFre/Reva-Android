package com.example.beardwulf.reva.activities

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.annotation.NonNull
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.example.beardwulf.reva.R
import kotlinx.android.synthetic.main.groepsfoto.*

class GroepsFoto : AppCompatActivity() {

    val MY_CAMERA_PERMISSION_CODE = 100;
    private final val CAMERA_REQUEST = 1888;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.groepsfoto)


        cmdPhoto.setOnClickListener {
            var cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }

        cmdNext.setOnClickListener {
            intent = Intent(this, InfoInvoer::class.java)
            startActivity(intent)
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
            photoView.setImageBitmap(photo)
            cmdNext.setEnabled(true);
        }
    }
}
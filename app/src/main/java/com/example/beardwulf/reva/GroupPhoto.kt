package com.example.beardwulf.reva

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.annotation.NonNull
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class GroupPhoto : AppCompatActivity() {

    private final val CAMERA_REQUEST = 1888;
    private lateinit var imageView: ImageView;
    lateinit var photoButton: Button;
    val MY_CAMERA_PERMISSION_CODE = 100;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_photo)
        this.imageView = findViewById(R.id.photoView);
        this.photoButton = findViewById(R.id.cmdPhoto);
        photoButton.setOnClickListener {

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent? )
    {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            var photo: Bitmap
            photo =data?.getExtras()?.get("data") as Bitmap
            imageView.setImageURI(photo);

        }
    }
}

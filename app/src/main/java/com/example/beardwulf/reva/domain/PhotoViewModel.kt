package com.example.beardwulf.reva.domain

import android.arch.lifecycle.ViewModel
import android.graphics.Bitmap
import android.net.Uri

class PhotoViewModel : ViewModel() {
    public lateinit var photo: Bitmap
    public lateinit var photoUri: Uri

}
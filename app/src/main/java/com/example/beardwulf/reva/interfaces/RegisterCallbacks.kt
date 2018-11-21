package com.example.beardwulf.reva.interfaces

import android.graphics.Bitmap
import android.net.Uri
import com.example.beardwulf.reva.domain.Category

interface RegisterCallbacks {
    fun setNameAndDescription(name : String, description : String)
    fun setPhoto(photo : Bitmap, photoUri : Uri)
    fun getPhoto(imageUri : Uri) : Bitmap
    fun setCategories(categories : List<String>)
}
package com.example.beardwulf.reva.interfaces

import android.graphics.Bitmap
import com.example.beardwulf.reva.domain.Category

interface RegisterCallbacks {
    fun setNameAndDescription(name : String, description : String)
    fun setPhoto(photo : Bitmap)
    fun setCategories(categories : List<Category>)
}
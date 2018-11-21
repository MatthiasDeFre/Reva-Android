package com.example.beardwulf.reva.interfaces

import android.graphics.Bitmap
import android.net.Uri
import com.example.beardwulf.reva.domain.Exhibitor

interface QuestionCallbacks {
    fun setNextExhibitor()
    fun setAnswer(answer : String)
    fun setAnswer(photo : Bitmap)
    fun setNextQuestion()
    fun decrementCounter()
    fun determineNextMove()
    fun getPhoto(imageUri : Uri) : Bitmap
    val currentExhibitor : Exhibitor
    val maxQuestion : Int
    val firstQuestion : Boolean
}
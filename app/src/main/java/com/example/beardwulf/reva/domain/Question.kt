package com.example.beardwulf.reva.domain

import com.google.gson.annotations.SerializedName
import java.util.*

class Question {

    @SerializedName("body")
    lateinit var body : String
    @SerializedName("posted")
    lateinit var posted: Date
    @SerializedName("possibleAnswers")
    lateinit var possibleAnswers: Array<String>
    @SerializedName("exhibitor")
    lateinit var exhibitor: Object


}
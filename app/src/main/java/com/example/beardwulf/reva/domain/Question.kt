package com.example.beardwulf.reva.domain

import com.google.gson.annotations.SerializedName
import java.util.*

class Question {

    @SerializedName("body")
    var body : String
    @SerializedName("posted")
    var posted: Date
    @SerializedName("possibleAnswers")
    var possibleAnswers: Array<String>
    @SerializedName("exhibitor")
    var exhibitor: Exhibitor

    constructor(body: String, posted: Date, possibleAnswers: Array<String>, exhibitor: Exhibitor) {
        this.body=body
        this.posted=posted
        this.possibleAnswers = possibleAnswers
        this.exhibitor = exhibitor
    }





}
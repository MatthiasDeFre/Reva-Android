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
    @SerializedName("counter")
    var counter : Int

    constructor(body: String, posted: Date, possibleAnswers: Array<String>, counter : Int) {
        this.body=body
        this.posted=posted
        this.possibleAnswers = possibleAnswers
        this.counter = counter
    }
}
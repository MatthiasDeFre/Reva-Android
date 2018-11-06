package com.example.beardwulf.reva.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Exhibitor : Serializable {

    @SerializedName("_id")
    var _id: String
    @SerializedName("name")
    var name: String?
    @SerializedName("visits")
    var visits: Number?
    @SerializedName("category")
    var category: String?
    @SerializedName("coordinates")
    var coordinates: Pair<Int, Int>?
    @SerializedName("question")
    var question: Question?


    constructor(_id: String, name: String?, visits: Number?, category: String?) {
        this._id = _id
        this.name = name
        this.visits = visits
        this.category = category
        this.coordinates = Pair(0, 0)
        this.question = null
    }

    constructor(_id: String, name: String?, visits: Number?, category: String?, coordinates: Pair<Int, Int>?) {
        this._id = _id
        this.name = name
        this.visits = visits
        this.category = category
        this.coordinates = coordinates
        this.question = null
    }

    constructor(_id: String, name: String?, visits: Number?, category: String?, coordinates: Pair<Int, Int>?, question: Question?) {
        this._id = _id
        this.name = name
        this.visits = visits
        this.category = category
        this.coordinates = coordinates
        this.question = question
    }


}
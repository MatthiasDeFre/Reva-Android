package com.example.beardwulf.reva.domain

import com.google.gson.annotations.SerializedName

class Exhibitor {

    @SerializedName("name")
    var name: String
    @SerializedName("visits")
    var visits: Number
    @SerializedName("category")
    var category: Category
    @SerializedName("coordinates")
    var coordinates: Pair<Int, Int>
    var questions: ArrayList<Int>


    constructor(name: String, visits: Number, category: Category) {
        this.name = name
        this.visits = visits
        this.category = category
        this.coordinates = Pair(0,0 )
        this.questions = ArrayList()
    }

    constructor(name: String, visits: Number, category: Category, coordinates: Pair<Int, Int>) {
        this.name = name
        this.visits = visits
        this.category = category
        this.coordinates = coordinates
        this.questions = ArrayList()
    }

    constructor(name: String, visits: Number, category: Category, coordinates: Pair<Int, Int>, questions: ArrayList<Int>) {
        this.name = name
        this.visits = visits
        this.category = category
        this.coordinates = coordinates
        this.questions = questions
    }


}
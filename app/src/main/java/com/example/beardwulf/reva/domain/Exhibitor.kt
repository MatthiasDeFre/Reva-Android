package com.example.beardwulf.reva.domain

import com.google.gson.annotations.SerializedName

class Exhibitor {

    @SerializedName("name")
    var name: String
    @SerializedName("visits")
    var visits: Number
    @SerializedName("category")
    var category: Category

    constructor(name: String, visits: Number, category: Category) {
        this.name = name
        this.visits = visits
        this.category = category
    }
}
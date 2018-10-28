package com.example.beardwulf.reva.domain

import com.google.gson.annotations.SerializedName

class Exhibitor {

    @SerializedName("name")
    lateinit var name: String
    @SerializedName("visits")
    lateinit var visits: Number
    @SerializedName("category")
    lateinit var category: Category
}
package com.example.beardwulf.reva

import com.example.beardwulf.reva.domain.Exhibitor
import com.example.beardwulf.reva.domain.Question
import retrofit2.Call
import retrofit2.http.GET
import java.util.*

interface Endpoint {

    @GET("/API/teachers/codes")
    fun getAllCodes(): Call<List<Int>>

    @GET("/API/student/exhibitor/:group")
    fun getExhibitor(objects: Objects): Call<Exhibitor>

    @GET("/API/student/group/:code")
    fun getGroup(int: Int): Call<Objects>
}
package com.example.beardwulf.reva

import com.example.beardwulf.reva.domain.Question
import retrofit2.Call
import retrofit2.http.GET

interface TestEndpoint {

    @GET("/questions")
    fun getAllQuestions(): Call<List<Question>>

    @GET("/API/teachers/codes")
    fun getAllCodes(): Call<List<Int>>
}
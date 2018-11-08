package com.example.beardwulf.reva

import com.example.beardwulf.reva.domain.Exhibitor
import com.example.beardwulf.reva.domain.Group
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.*

interface Endpoint {

    @GET("/API/student/group/{code}")
    fun getGroup(@Path("code") code: String): Call<Group>

    @POST("/API/student/register/{group}")
    @FormUrlEncoded
    fun registerGroup(@Path("group") group: String): Call<Group>

    @GET("/API/student/exhibitor/{group}")
    fun getExhibitor(@Path("group") group: String): Call<Exhibitor>

    @POST("API/student/answer/{group}")
    @FormUrlEncoded
    fun postAnwser(@Path("group") group: String): Call<Group>
}
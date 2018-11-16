package com.example.beardwulf.reva

import com.example.beardwulf.reva.domain.Category
import com.example.beardwulf.reva.domain.Exhibitor
import com.example.beardwulf.reva.domain.Group
import retrofit2.Call
import retrofit2.http.*
import java.util.*
import retrofit2.http.GET
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.POST
import retrofit2.http.Multipart
import kotlin.collections.ArrayList


interface Endpoint {

    @GET("/API/student/group/{code}")
    fun getGroup(@Path("code") code: String): Call<Group>

    @POST("/API/student/register/{group}")
    @Multipart
    fun registerGroup(@Path("group") group: String, @Part groupImage: MultipartBody.Part, @Part("description") description: String?, @Part("name") name: String?, @Part("categories") categories: ArrayList<Category>): Call<Group>

    @POST("/API/student/exhibitor/{group}")
    fun getExhibitor(@Path("group") group: String): Call<Exhibitor>

    @POST("API/student/answer/{group}")
    @FormUrlEncoded
    fun postAnwser(@Path("group") group: String, @Body answer : String): Call<Group>

}
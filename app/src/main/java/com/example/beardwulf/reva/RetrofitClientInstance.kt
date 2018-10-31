package com.example.beardwulf.reva

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


class RetrofitClientInstance {
    private var retrofit: Retrofit? = null
    private val BASE_URL = "http://projecten3studserver08.westeurope.cloudapp.azure.com"

    fun getRetrofitInstance(): Retrofit? {
        if (retrofit == null) {
            retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit
    }
}
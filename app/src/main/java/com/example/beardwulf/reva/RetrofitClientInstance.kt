package com.example.beardwulf.reva

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


class RetrofitClientInstance {
    private var retrofit: Retrofit? = null
    private val BASE_URL = "http://projecten3studserver08.westeurope.cloudapp.azure.com/?fbclid=IwAR1kGWZ0BjoRpt51HTU_GlSdO_b4QdPi1hO_OzLM9DGNCW35Fk3MHXZJBTA"

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
package com.example.jetbackcompose.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiManager private constructor() {
    companion object {
        private var Instance: Retrofit? = null
        private fun getInstance(): Retrofit {

            if (Instance == null) {

                val loggingInterceptor = HttpLoggingInterceptor {
                    Log.e("api", it)
                }
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
                // Create a Retrofit instance.
                val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build()
                Instance = Retrofit
                    .Builder()
                    .client(okHttpClient)
                    .baseUrl("https://newsapi.org/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return Instance!!

        }


        fun getApis(): WebServices {
            // Create an instance of the WebServices interface to get apis
            return getInstance().create(WebServices::class.java)
        }
    }


}
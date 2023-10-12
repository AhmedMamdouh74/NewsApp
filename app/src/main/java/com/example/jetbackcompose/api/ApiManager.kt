package com.example.jetbackcompose.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager private constructor() {
    companion object {
        private var Instance: Retrofit? = null
        private fun getInstance(): Retrofit {

            if (Instance == null)
            // Create a Retrofit instance.
                Instance = Retrofit
                    .Builder()
                    .baseUrl("https://newsapi.org/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return Instance!!
        }

        fun getApis(): WebServices {
            // Create an instance of the WebServices interface to get apis
            return getInstance().create(WebServices::class.java)
        }
    }


}
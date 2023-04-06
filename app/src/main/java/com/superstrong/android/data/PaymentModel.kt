package com.superstrong.android.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

class PaymentModel {
}

data class PayInfo(
    val to_address: String,
    val send_amount: Double,
    val coin_name: String
)

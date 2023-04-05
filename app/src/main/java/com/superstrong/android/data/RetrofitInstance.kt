package com.superstrong.android.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    var retrofitInstance: RetrofitInstance? = null
    var retrofitService: RetrofitServiceApi? = null

    // BaseUrl등록
    private val BASE_URL = "http://localhost:8080/"

    private fun RetrofitInstance() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL) // Json을 변환해줄 Gson변환기 등록
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofitService = retrofit.create(RetrofitServiceApi::class.java)
    }

//    fun getRetrofitInstance(): RetrofitInstance? { // 싱글톤
//        if (retrofitInstance == null) {
//            retrofitInstance = RetrofitInstance()
//        }
//        return retrofitInstance
//    }

    fun getRetrofitService(): RetrofitServiceApi? {
        return retrofitService
    }
}
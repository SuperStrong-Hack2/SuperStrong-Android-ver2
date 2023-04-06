package com.superstrong.android.data

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

public interface BackendApiService {
    @POST("api/login") // 엔드포인트 지정
    fun login(@Body user: User): Call<String> // 요청 바디에 User 객체 전달

    @POST("api/sendinput")
    fun payment(@Body payInfo: PayInfo ): Call<String>

}

class RetrofitApiService {

}

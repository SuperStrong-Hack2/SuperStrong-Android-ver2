package com.superstrong.android.data
import android.content.Context
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.superstrong.android.databinding.ActivityLoginBinding
import com.superstrong.android.view.LoginActivity

data class User(
    val id: String,
    val pw: String
)

interface BackendApiService {
    @POST("api/login") // 엔드포인트 지정
    fun login(@Body user: User): Call<String> // 요청 바디에 User 객체 전달
}

object RetrofitClient {
    private const val BASE_URL = "http://localhost:8080/" // 백엔드의 기본 URL을 입력해주세요

    val backendApiService: BackendApiService by lazy {
        val gson: Gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        retrofit.create(backendApiService::class.java)
    }
}
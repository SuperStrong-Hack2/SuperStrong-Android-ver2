package com.superstrong.android.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

public interface BackendApiService {
    @POST("api/login")
    fun login(@Body user: User): Call<JsonObject>

    // Payment1 -> Payment2 넘어갈 때 POST
    @POST("api/send_input")
    fun payment1(@Body payInfo1: PayInfo1): Call<JsonObject>

    // Payment2 -> Payment3 넘어갈 때 POST
    @POST("api/send_input")
    fun payment2(@Body payInfo2: PayInfo2): Call<JsonObject>


    @POST("api/register")
    @Headers("Content-Type: application/json")
    suspend fun signUp(@Body body: SignUpRequestBody): Response<SignUpResponseBody>

    /*@POST("api/auth")
    @Headers("Content-Type: application/json")
    fun emailAuth(@Body body: authCode): Call<UserData>*/

    @POST("api/register/auth")
    @Headers("Content-Type: application/json")
    suspend fun emailAuth2(@Body body: authCode): Response<UserData>

}
object RetrofitInstance {
    private const val BASE_URL = "https://15e5-222-236-64-196.jp.ngrok.io"
    private val gson: Gson = GsonBuilder().create()
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val backendApiService: BackendApiService by lazy {
        retrofit.create(BackendApiService::class.java)
    }
}

class RetrofitApiService {

}
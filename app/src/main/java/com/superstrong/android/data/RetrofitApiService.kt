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
    fun login(@Body encryptedData: EncryptedData): Call<JsonObject>

    // Payment1 -> Payment2 넘어갈 때 POST
    @POST("api/send_input")
    fun payment1(@Body encryptedData: EncryptedPayment): Call<JsonObject>

    //Payment1 -> Payment2 넘어갈 때 GET
    @POST("api/send_input")
    fun paymentRsp1(@Body payInfoRsp1: PayInfoRsp1): Call<JsonObject>

    // Payment2 -> Payment3 넘어갈 때 POST
    @POST("api/send")
    fun payment2(@Body encryptedData: EncryptedPayment): Call<JsonObject>


    @POST("api/register")
    @Headers("Content-Type: application/json")
    suspend fun signUp(@Body body: E2eReq): Response<E2eRes>

    /*@POST("api/auth")
    @Headers("Content-Type: application/json")
    fun emailAuth(@Body body: authCode): Call<UserData>*/

    @POST("api/register/auth")
    @Headers("Content-Type: application/json")
    suspend fun emailAuth(@Body body: E2eReq): Response<E2eRes>

    @POST("api/chpass")
    @Headers("Content-Type: application/json")
    suspend fun chPass(@Body body: Password): Response<ChpassReponse>

    @POST("api/chpass/newPass")
    @Headers("Content-Type: application/json")
    suspend fun newPass(@Body body: Password): Response<ChpassReponse>

    @POST("api/main_asset")
    @Headers("Content-Type: application/json")
    suspend fun getBalance(@Body body: E2eReq2): Response<E2eRes>

    @POST("/api/main_history")
    @Headers("Content-Type: application/json")
    suspend fun getHistory(@Body body: E2eReq2): Response<E2eRes>
}

object RetrofitInstance {
    private const val BASE_URL = "https://03db-222-236-64-196.ngrok-free.app"
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


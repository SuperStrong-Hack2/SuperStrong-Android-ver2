package com.superstrong.android.data


import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class SignUpRequestBody(
    @SerializedName("id")
    val id:String,
    @SerializedName("pw")
    val pw:String,
    @SerializedName("email")
    val email:String,
    @SerializedName("ssn")
    val ssn:String,
    @SerializedName("phone_num")
    val phone_num: String
)

data class SignUpResponseBody(
    @SerializedName("res")
    val result: String,
)

data class authCode(
    @SerializedName("code")
    var code:String
)

data class UserData(
    @SerializedName("res") // 맞으면 1 틀리면 0
    var result: String,
    @SerializedName("private_key") // 거래시 사용할 키
    var key:String,
    @SerializedName("pub_address") // 지갑 주소
    var pubAddress:String,
    @SerializedName("id") // 사용자 id
    var id:String
)

class Repository {
    val retrofitService = RetrofitInstance.backendApiService
    suspend fun signupRequest(body: SignUpRequestBody) = withContext(Dispatchers.IO) {
        return@withContext retrofitService.signUp(body)
    }
}


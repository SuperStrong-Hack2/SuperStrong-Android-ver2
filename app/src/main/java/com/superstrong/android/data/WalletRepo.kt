package com.superstrong.android.data

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.superstrong.android.viewmodel.AES256Util2

data class Balance(
    @SerializedName("validation")
    val valid:Int,
    @SerializedName("eth")
    val eth:Double,
    @SerializedName("doge")
    val doge:Double,
    @SerializedName("btc")
    val btc:Double
)

data class Id(
    @SerializedName("id")
    val id :String
)

data class History(
    @SerializedName("type")
    val type:Int,
    @SerializedName("coin")
    val coin:Int,
    @SerializedName("amount")
    val amount:Double,
    @SerializedName("date")
    val date:String,
    @SerializedName("address")
    val address:String
)

class WalletRepo :BaseRepo() {
    val retrofitService = RetrofitInstance.backendApiService
    suspend fun getBalance(body: Id, token:String, key:String) :Balance?{
        AES256Util2.init2(key)
        val req = E2eReq2(AES256Util2.aesEncode(Gson().toJson(body)), token)
        var job = safeApiCall { retrofitService.getBalance(req) }
        val res = job.data

        if(res == null)
            return null
        else
            return Gson().fromJson(AES256Util2.aesDecode(res.encData), Balance::class.java)
    }

}
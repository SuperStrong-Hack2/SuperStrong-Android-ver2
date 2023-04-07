package com.superstrong.android.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.superstrong.android.data.*
import com.superstrong.android.view.FindPassActivity
import com.superstrong.android.view.PaymentActivity2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaymentVModel : ViewModel() {
    val toaddress = MutableLiveData<String>()
    val sendamount = MutableLiveData<Double>()
    val coinname = MutableLiveData<String>()

    fun PostPayment(to_address: String, send_amount: Double, coin_name: String, context: Context) {
        val payInfo1 = PayInfo1(to_address, send_amount, coin_name) // 전송할 데이터 모델 객체 생성
        Log.d("!!!!!!!!!!!!!!!", "df" + payInfo1)
        val call = RetrofitInstance.backendApiService.payment1(payInfo1) // POST 요청 보내기

        call.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()?.toString()
                    val jsonObject = Gson().fromJson(responseBody, JsonObject::class.java)
                    Log.i("리스폰스","reponse:"+responseBody)
                    if (jsonObject.get("token").asString == "invalid") {
                        Toast.makeText(context, "송금에 실패했습니다.", Toast.LENGTH_SHORT).show()
                    } else {
                        val intent = Intent(context, PaymentActivity2::class.java)
                        context.startActivity(intent)
                    }
                } else {
                    val errorBody = response.errorBody()?.toString()
                    Toast.makeText(context, "송금에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Toast.makeText(context, "통신 실패", Toast.LENGTH_SHORT).show()

            }
        })
    }

//    fun GetPayment(to_address: String, send_amount: Double, coin_name: String, context: Context) {
//        val payInfoRsp1 = PayInfoRsp1(to_address, send_amount, coin_name) // 전송할 데이터 모델 객체 생성
//        Log.d("!!!!!!!!!!!!!!!", "df" + payInfoRsp1)
//        val call = RetrofitInstance.backendApiService.payment1(payInfoRsp1) // POST 요청 보내기
//
//        call.enqueue(object : Callback<JsonObject> {
//            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
//                if (response.isSuccessful) {
//                    val responseBody = response.body()?.toString()
//                    val jsonObject = Gson().fromJson(responseBody, JsonObject::class.java)
//                    Log.i("리스폰스","reponse:"+responseBody)
//                    if (jsonObject.get("token").asString == "invalid") {
//                        Toast.makeText(context, "송금에 실패했습니다.", Toast.LENGTH_SHORT).show()
//                    } else {
//                        val intent = Intent(context, PaymentActivity2::class.java)
//                        context.startActivity(intent)
//                    }
//                } else {
//                    val errorBody = response.errorBody()?.toString()
//                    Toast.makeText(context, "송금에 실패했습니다.", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
//                Toast.makeText(context, "통신 실패", Toast.LENGTH_SHORT).show()
//
//            }
//        })
//    }


}
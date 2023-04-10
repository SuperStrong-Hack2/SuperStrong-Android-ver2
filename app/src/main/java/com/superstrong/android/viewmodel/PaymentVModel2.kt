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

class PaymentVModel2 : ViewModel() {
    val toaddress = MutableLiveData<String>()
    val sendamount = MutableLiveData<Double>()
    val remainamount = MutableLiveData<Double>()
    val circulatedgas = MutableLiveData<Double>()
    val coinname = MutableLiveData<String>()
    val token = MutableLiveData<String>()
    val id = MutableLiveData<String>()

    fun PostPayment(to_address: String, send_amount: Double, coin_name: String, remain_amount: Double, circulated_gas: Double, token:String, id:String, context: Context) {
        val payInfo2 = PayInfo2(to_address, send_amount, coin_name, remain_amount, circulated_gas, token, id) // 전송할 데이터 모델 객체 생성
        Log.d("액티비티 2에서 3으로 넘어갈때!!! 넘기는 값들 !!!!!!!!", "df" + payInfo2)
        val call = RetrofitInstance.backendApiService.payment2(payInfo2) // POST 요청 보내기

        call.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {

                    val responseBody = response.body()?.toString()
                    val jsonObject = Gson().fromJson(responseBody, JsonObject::class.java)
                    Log.i("리스폰스","reponse:"+responseBody)
                    if (jsonObject.get("invalid input").asString == "no destination" && jsonObject.get("invalid input").asString != null) {
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
                Toast.makeText(context, "통신 실패: PaymentVModel2", Toast.LENGTH_SHORT).show()

            }
        })
    }}
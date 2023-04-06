package com.superstrong.android.viewmodel

import RetrofitInstance
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.superstrong.android.data.PayInfo
import com.superstrong.android.data.RetrofitClient
import com.superstrong.android.data.RetrofitApiService
import com.superstrong.android.data.User
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
        val payInfo = PayInfo(to_address, send_amount, coin_name) // 전송할 데이터 모델 객체 생성

        val call = RetrofitInstance.backendApiService.payment(payInfo) // POST 요청 보내기

        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {

                    val responseBody = response.body()?.toString()
                    if (responseBody == "Payment Failed") {
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

            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(context, "통신 실패", Toast.LENGTH_SHORT).show()

            }
        })
    }}
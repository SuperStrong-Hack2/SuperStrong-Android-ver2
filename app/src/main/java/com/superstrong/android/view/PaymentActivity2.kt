package com.superstrong.android.view

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.superstrong.android.R
import com.superstrong.android.databinding.ActivityPayment2Binding
import com.superstrong.android.viewmodel.PaymentGetVModel
import com.superstrong.android.viewmodel.PaymentVModel
import com.superstrong.android.viewmodel.PaymentVModel2

class PaymentActivity2 : AppCompatActivity() {

    private var coin_name: String? = ""
    private lateinit var binding: ActivityPayment2Binding
    private lateinit var paymentVModel2: PaymentVModel2
    private lateinit var paymentGetVModel: PaymentGetVModel
    var id:String ?= "ida"
    var token:String ?= "abababababab"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment2)
        paymentVModel2 = ViewModelProvider(this).get(PaymentVModel2::class.java)
        paymentGetVModel = ViewModelProvider(this).get(PaymentGetVModel::class.java)

        // 네비게이션
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.navigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener(MyNavigationListener(this))

        // 바인딩
        binding = ActivityPayment2Binding.inflate(layoutInflater)
        setContentView(binding.root)

// --------------- 서버에서 정보 GET -------------------------------------------------------------------------------------------

        // 지갑의 주소 받기
        val to_address: String? = paymentGetVModel.toaddress.value
        binding.payAddress.setText(to_address)

        // 코인의 수량 받기
        //val send_amount = intent.getDoubleExtra("ToCoinQuan")
        val send_amount: Double? = paymentGetVModel.sendamount.value
        binding.coinPay.setText(send_amount.toString())
        Log.d("Send Amount","전송 수량 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! - " + send_amount)

        // 코인의 이름 받기
        val coin_name: String? = paymentGetVModel.coinname.value
        binding.coin1.setText(coin_name)
        binding.coin2.setText(coin_name)
        binding.coin3.setText(coin_name)

        // 코인의 가스 비용 받기
        val circulated_gas: Double? = paymentGetVModel.circulated_gas.value
        binding.coinGas.setText(circulated_gas.toString())

        // 코인의 잔금 받기
        val remain_amount: Double? = paymentGetVModel.remain_amount.value
        binding.coinGas.setText(remain_amount.toString())

        if (to_address != null && send_amount != null && coin_name != null && circulated_gas != null && remain_amount != null) {
            paymentGetVModel.GetPayment(to_address, send_amount, coin_name, circulated_gas, remain_amount, this)
            startActivity(intent)

        } else {
            Log.d("*서버에서가져옴* To Address","to address: "+to_address)
            Log.d("*서버에서가져옴* Send Amount","send amount: "+send_amount)
            Log.d("*서버에서가져옴* Coin name","coin name: "+coin_name)
            Log.d("*서버에서가져옴* Circulated Gas","circulated_gas: "+circulated_gas)
            Log.d("*서버에서가져옴* Remain Amount","remain_amount: "+remain_amount)
            Toast.makeText(this, "오류!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!", Toast.LENGTH_SHORT).show()
        }


//-----------------------------------------------------------------------------------------------------------------

        // 툴바 뒤로가기 버튼
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)
            finish()
        }

// ----------------- 서버에 정보 POST ------------------------------------------------------------------------------

        binding.nextButton.setOnClickListener {
            val intent = Intent(this, PaymentActivity3::class.java)

//            val sharedPref = getSharedPreferences("strong", Context.MODE_PRIVATE)
//            var token = sharedPref.getString("jwt_token","")
//            token = paymentVModel2.token.value
//            var id = sharedPref.getString("id","")
//            id = paymentVModel2.id.value


            // 코인 전송할 주소 전달
            val to_address: String? = paymentVModel2.toaddress.value
            intent.putExtra("ToAddress", to_address)

            // 전송할 코인의 수량 전달
            val send_amount: Double? = paymentVModel2.sendamount.value
            intent.putExtra("ToCoinQuan", send_amount)

            // 코인 종류 전달
            val coin_name: String? = paymentVModel2.coinname.value
            intent.putExtra("ToCoinName", coin_name)

            // 코인 잔금
            val remain_amount: Double? = paymentVModel2.remainamount.value
            intent.putExtra("ToCoinName", remain_amount)

            // 가스 요금
            val circulated_gas: Double? = paymentVModel2.circulatedgas.value
            intent.putExtra("ToCoinName", circulated_gas)

            if (to_address != null && send_amount != null && coin_name != null && circulated_gas != null && remain_amount != null && token != null && id != null) {
                paymentVModel2.PostPayment(to_address, send_amount, coin_name, circulated_gas, remain_amount, token!!, id!!, this)
                startActivity(intent)

            } else {
                Log.d("To Address","to address: "+to_address)
                Log.d("Send Amount","send amount: "+send_amount)
                Log.d("Coin name","coin name: "+coin_name)
                Log.d("Circulated Gas","circulated_gas: "+circulated_gas)
                Log.d("Remain Amount","remain_amount: "+remain_amount)
                Log.d("Token","token: "+token)
                Log.d("ID","id: "+id)
                Toast.makeText(this, "오류!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

}

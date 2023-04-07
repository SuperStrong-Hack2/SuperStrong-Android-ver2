package com.superstrong.android.view

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.superstrong.android.R
import com.superstrong.android.databinding.ActivityPayment2Binding
import com.superstrong.android.viewmodel.PaymentVModel2

class PaymentActivity2 : AppCompatActivity() {

    private var coin_name: String? = ""
    private lateinit var binding: ActivityPayment2Binding
    private lateinit var paymentVModel2: PaymentVModel2

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment2)

        // 네비게이션
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.navigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener(MyNavigationListener(this))

        // 바인딩
        binding = ActivityPayment2Binding.inflate(layoutInflater)
        setContentView(binding.root)

// --------------- 서버에서 정보 GET -------------------------------------------------------------------------------------------

        // PaymentActivity에서 값을 가져와서 뿌려주기
        // 전송할 지갑의 주소
        val to_address = intent.getStringExtra("to_address")
        binding.payAddress.setText(to_address)

        // 전송할 코인의 수량
//        val send_amount = intent.getDoubleExtra("ToCoinQuan")
//        binding.coinPay.setText(send_amount.toString())
//        Log.d("Send Amount","전송 수량 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! - " + send_amount)

        // 전송할 코인의 이름
        val coin_name = intent.getStringExtra("coin_name")
        binding.coin1.setText(coin_name)
        binding.coin2.setText(coin_name)
        binding.coin3.setText(coin_name)

        // 전송할 코인의 가스 비용
//        val to_coingas = intent.getStringExtra("")
//        binding.coinGas.setText(to_coingas)
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

            startActivity(intent)
        }
    }

}

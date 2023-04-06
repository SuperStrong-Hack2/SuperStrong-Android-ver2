package com.superstrong.android.view

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.superstrong.android.R
import com.superstrong.android.databinding.ActivityPayment2Binding
import com.superstrong.android.databinding.ActivityPaymentBinding

class PaymentActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityPayment2Binding

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

        // PaymentActivity에서 값을 가져와서 뿌려주기
        // 전송할 지갑의 주소
        val to_address = intent.getStringExtra("ToAddress")
        binding.payAddress.setText(to_address)

        // 전송할 코인의 수량
        val to_coinquan = intent.getStringExtra("ToCoinQuan")
        binding.coinPay.setText(to_coinquan)

        // 전송할 코인의 이름
        val to_coinname = intent.getStringExtra("ToCoinName")
        binding.coin1.setText(to_coinname)
        binding.coin2.setText(to_coinname)
        binding.coin3.setText(to_coinname)

        // 전송할 코인의 가스 비용
//        val to_coingas = intent.getStringExtra("")
//        binding.coinGas.setText(to_coingas)


        // 툴바 뒤로가기 버튼
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.nextButton.setOnClickListener {
            val intent = Intent(this, PaymentActivity3::class.java)

            // 코인 전송할 주소 전달
            intent.putExtra("ToAddress", to_address)

            // 전송할 코인의 수량 전달
            intent.putExtra("ToCoinQuan", to_coinquan)

            // 코인 종류 전달
            intent.putExtra("ToCoinName", to_coinname)

            startActivity(intent)
        }
    }

}
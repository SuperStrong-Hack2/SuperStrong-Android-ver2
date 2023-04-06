package com.superstrong.android.view

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.superstrong.android.R
import com.superstrong.android.databinding.ActivityPaymentBinding
import java.sql.Types.NULL

class PaymentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentBinding
    private var coin_name: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        // 네비게이션 바
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.navigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener(MyNavigationListener(this))

        // 바인딩
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 툴바 뒤로가기 버튼
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, WalletActivity::class.java)
            startActivity(intent)
            finish()
        }

        // 스피너 구현
        binding.spinner.adapter = ArrayAdapter.createFromResource(this, R.array.itemList, android.R.layout.simple_spinner_item)
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) { }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    // BTC
                    0 -> {
                        coin_name = "BTC"
                    }
                    // ETH
                    1 -> {
                        coin_name = "ETH"
                    }
                    // DOGE
                    2 -> {
                        coin_name = "DOGE"
                    }
                    else -> {
                    }
                }
            }
        }

        //다음 버튼을 눌렀을 때 코인 주소, 코인의 수량, 코인 종류 전달
        binding.nextButton.setOnClickListener {
            val intent = Intent(this, PaymentActivity2::class.java)

            // 코인 전송할 주소 전달
            val to_address = binding.sampleEditText.text.toString()
            intent.putExtra("ToAddress", to_address)

            // 전송할 코인의 수량 전달
            val to_coin = binding.editCoin.text.toString()
            intent.putExtra("ToCoinQuan", to_coin)

            // 코인 종류 전달
            intent.putExtra("ToCoinName", coin_name)
            Log.d(TAG, coin_name)

            startActivity(intent)

        }

    }

}
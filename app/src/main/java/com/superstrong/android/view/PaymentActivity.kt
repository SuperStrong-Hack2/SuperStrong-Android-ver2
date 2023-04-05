package com.superstrong.android.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.superstrong.android.R
import com.superstrong.android.databinding.ActivityPaymentBinding

class PaymentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.navigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener(MyNavigationListener(this))

//        val NextButton : Button = findViewById(R.id.next_button);
//        // findViewById 대신에 ViewBinding을 사용한다
//
//        val BackButton : ImageView = findViewById(R.id.btn_back);
//
//        NextButton.setOnClickListener {
//            val intent = Intent(this, PaymentActivity2::class.java)
//            startActivity(intent)
//        }
//
//        BackButton.setOnClickListener {
//            val intent = Intent(this, PaymentActivity2::class.java)
//            startActivity(intent)
//        }

        // 툴바 뒤로가기 버튼
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, WalletActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.nextButton.setOnClickListener {
            val intent = Intent(this, PaymentActivity2::class.java)
            startActivity(intent)
        }
    }

}
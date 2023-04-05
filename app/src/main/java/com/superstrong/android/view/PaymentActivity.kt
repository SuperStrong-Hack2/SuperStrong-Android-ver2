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
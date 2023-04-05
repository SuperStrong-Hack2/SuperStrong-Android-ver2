package com.superstrong.android.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.superstrong.android.R
// import com.superstrong.android.databinding.ActivityWalletBinding
import com.superstrong.android.viewmodel.TabFragmentAdapter

class WalletActivity : AppCompatActivity() {

//    private lateinit var binding: ActivityWalletBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityWalletBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        setContentView(R.layout.activity_wallet)

        val pagerAdapter = TabFragmentAdapter(supportFragmentManager)

        val pager = findViewById<ViewPager>(R.id.viewPager)
        pager.adapter = pagerAdapter

        val tab = findViewById<TabLayout>(R.id.tab_layout1)
        tab.setupWithViewPager(pager)

        val PaymentBtn : ImageView = findViewById(R.id.imgPayment)
        val TimeCoinBtn : ImageView = findViewById(R.id.imgTimecoin)
        val SwapBtn : ImageView = findViewById(R.id.imgSwap)

        PaymentBtn.setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)
        }

        TimeCoinBtn.setOnClickListener {
            val intent = Intent(this, TimeCoinActivity::class.java)
            startActivity(intent)
        }

        SwapBtn.setOnClickListener {
            val intent = Intent(this, SwapActivity::class.java)
            startActivity(intent)
        }

//
//        binding.imgPayment.setOnClickListener {
//            Log.d("태그", "송금 클릭했다")
//            val intent = Intent(this, PaymentActivity::class.java)
//            startActivity(intent)
//        }
//
//        binding.imgTimecoin.setOnClickListener {
//            val intent = Intent(this, TimeCoinActivity::class.java)
//            startActivity(intent)
//        }
//
//        binding.imgSwap.setOnClickListener {
//            val intent = Intent(this, SwapActivity::class.java)
//            startActivity(intent)
//        }

    }

}
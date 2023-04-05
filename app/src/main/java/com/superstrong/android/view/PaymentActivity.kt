package com.superstrong.android.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
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

        // 스피너 구현

        binding.spinner.adapter = ArrayAdapter.createFromResource(this, R.array.itemList, android.R.layout.simple_spinner_item)

        //아이템 선택 리스너
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    //선택안함
                    0 -> {
//                        title_tv.setText("선택안함")
//                        name_tv.setText("")
//                        content_tv.setText("내용 없음.")
                    }
                    // BTC
                    1 -> {
//                        title_tv.setText(spinner.selectedItem.toString())
//                        name_tv.setText("정수아")
//                        content_tv.setText("시 내용 입력 (생략)")
                    }
                    // ETH
                    2 -> {
//                        title_tv.setText(spinner.selectedItem.toString())
//                        name_tv.setText("박준기")
//                        content_tv.setText("시 내용 입력 (생략)")
                    }
                    // DOGE
                    3 -> {
//                        title_tv.setText(spinner.selectedItem.toString())
//                        name_tv.setText("박준기")
//                        content_tv.setText("시 내용 입력 (생략)")
                    }
                    else -> {
//                        title_tv.setText("메뉴")
//                        name_tv.setText("")
//                        content_tv.setText("")
                    }
                }
            }
        }

    }

}
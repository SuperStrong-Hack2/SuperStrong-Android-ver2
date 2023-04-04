package com.superstrong.android.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.superstrong.android.R

class SwapActivity2 : AppCompatActivity() {

//    // 전역 변수로 바인딩 객체 선언
//    private var mBinding: PaymentActivityMainBinding? = null
//    // 매번 null 체크를 할 필요 없이 편의성을 위해 바인딩 변수 재 선언
//    private val binding get() = mBinding!!

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swap2)

        val NextButton : Button = findViewById(R.id.confirm_button)
        // findViewById 대신에 ViewBinding을 사용한다

        NextButton.setOnClickListener {
            val intent = Intent(this, WalletActivity::class.java)
            startActivity(intent)
        }
    }

}
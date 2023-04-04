package com.superstrong.android.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.superstrong.android.R

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        // val NextButton : Button = findViewById(R.id.next_button);
        // findViewById 대신에 ViewBinding을 사용한다

//        NextButton.setOnClickListener {
//            val intent = Intent(this, PaymentActivity2::class.java)
//            startActivity(intent)
//        }
    }

}
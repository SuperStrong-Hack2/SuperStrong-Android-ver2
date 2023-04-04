package com.superstrong.android.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.superstrong.android.R

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val NextButton : Button = findViewById(R.id.confirm_button)
        val CancelButton: Button = findViewById(R.id.cancel_button)
        NextButton.setOnClickListener {
            val intent = Intent(this, SignupActivity2::class.java)
            startActivity(intent)
            finish()
        }
        CancelButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}
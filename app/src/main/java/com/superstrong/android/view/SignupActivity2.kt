package com.superstrong.android.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.superstrong.android.R

class SignupActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup2)

        val NextButton : Button = findViewById(R.id.confirm_button)
        NextButton.setOnClickListener {
            val intent = Intent(this, SignupActivity3::class.java)
            startActivity(intent)
            finish()
        }
    }

}
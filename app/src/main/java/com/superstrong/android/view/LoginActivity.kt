package com.superstrong.android.view


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.superstrong.android.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val LoginButton : Button = findViewById(R.id.LoginBtn)
        // findViewById 대신에 ViewBinding을 사용한다
        val SignupButton : Button = findViewById(R.id.SignupBtn)
        val PassFindButton : Button = findViewById(R.id.FindPassBtn)

        LoginButton.setOnClickListener {
            val intent = Intent(this, WalletActivity::class.java)
            startActivity(intent)
            finish()
        }

        SignupButton.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
        PassFindButton.setOnClickListener {
            val intent = Intent(this, FindPassActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}
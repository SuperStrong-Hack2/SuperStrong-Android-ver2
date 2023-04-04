package com.superstrong.android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.superstrong.android.R

class AccountManageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_manage)
        val chpasswd = findViewById<TextView>(R.id.manage_chpass)
        val intent = Intent(this,)
        chpasswd.setOnClickListener {

        }
    }
}
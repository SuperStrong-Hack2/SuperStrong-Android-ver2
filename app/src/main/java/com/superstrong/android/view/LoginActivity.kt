package com.superstrong.android.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.superstrong.android.R
import com.superstrong.android.databinding.ActivityLoginBinding
import com.superstrong.android.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        val usernameValue: String? = loginViewModel.username.value
        val passwordValue: String? = loginViewModel.password.value

        if (usernameValue != null && passwordValue != null) {
            loginViewModel.PostLogin(usernameValue, passwordValue, this)
        } else {
            Toast.makeText(this, "id와 password를 입력해 주세요", Toast.LENGTH_SHORT).show()
        }
        binding.loginBtn.setOnClickListener {
            val intent = Intent(this, WalletActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.signupBtn.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        binding.findPassBtn.setOnClickListener {
            val intent = Intent(this, FindPassActivity::class.java)
            startActivity(intent)
        }
    }
}
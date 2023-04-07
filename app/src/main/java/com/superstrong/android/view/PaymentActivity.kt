package com.superstrong.android.view

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.superstrong.android.R
import com.superstrong.android.databinding.ActivityPaymentBinding
import com.superstrong.android.viewmodel.LoginViewModel
import com.superstrong.android.viewmodel.PaymentVModel
import java.sql.Types.NULL

class PaymentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentBinding
    private var coin_name: String? = ""
    private lateinit var paymentVModel: PaymentVModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        paymentVModel = ViewModelProvider(this).get(PaymentVModel::class.java)

        // 네비게이션 바
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.navigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener(MyNavigationListener(this))

        // 바인딩
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 툴바 뒤로가기 버튼
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, WalletActivity::class.java)
            startActivity(intent)
            finish()
        }

        // 스피너 구현
        binding.spinner.adapter = ArrayAdapter.createFromResource(this, R.array.itemList, android.R.layout.simple_spinner_item)
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) { }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    // BTC
                    0 -> {
                        coin_name = "btc"
                    }
                    // ETH
                    1 -> {
                        coin_name = "eth"
                    }
                    // DOGE
                    2 -> {
                        coin_name = "doge"
                    }
                    else -> {
                    }
                }
            }
        }

//------------------서버에게 데이터 POST-------------------------------------------------------------------------

        binding.nextButton.setOnClickListener {
            val intent = Intent(this, PaymentActivity2::class.java)

            // 코인 전송할 주소 전달
            val to_address: String? = paymentVModel.toaddress.value
            intent.putExtra("to_address", to_address)

            // 전송할 코인의 수량 전달
            val send_amount: Double? = paymentVModel.sendamount.value
            intent.putExtra("send_amount", send_amount)

            // 코인 종류 전달
            val coin_name: String? = paymentVModel.coinname.value
            intent.putExtra("coin_name", coin_name)

            // id
//            val user_id: String? = paymentVModel.coinname.value
//            intent.putExtra("coin_name", coin_name)

            // token
//            val user_token: String? = paymentVModel.coinname.value
//            intent.putExtra("coin_name", coin_name)

            if (to_address != null && send_amount != null && coin_name != null) {
                paymentVModel.PostPayment(to_address, send_amount, coin_name, this)
                startActivity(intent)

            } else {
                Log.d("To Address","to address: "+to_address)
                Log.d("Send Amount","send amount: "+send_amount)
                Log.d("Coin name","coin name: "+coin_name)
                Toast.makeText(this, "입력한 주소와 코인의 종류, 수량을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }


        // 바인딩 체인지 리스너

        binding.sampleEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                paymentVModel.toaddress.value = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.editCoin.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val amount = s.toString().toDouble()
                paymentVModel.sendamount.value = amount
                //paymentVModel.sendamount.value = s.toString().toDouble()

            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position)?.toString()
                paymentVModel.coinname.value = selectedItem ?: ""
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                paymentVModel.toaddress.value = ""
            }
        }


    }

}
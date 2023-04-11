package com.superstrong.android.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.superstrong.android.databinding.ActivityWalletBinding
import com.superstrong.android.viewmodel.LogVModel
import com.superstrong.android.viewmodel.TabFragmentAdapter

class WalletActivity : FragmentActivity()  {

//    private lateinit var binding: ActivityWalletBinding
    private val tabTitles:Array<String> = arrayOf("잔금", "거래내용")
    lateinit var binding : ActivityWalletBinding
    lateinit var viewModel : LogVModel

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWalletBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_wallet)
        viewModel = ViewModelProvider(this).get(LogVModel::class.java)
        binding.viewModel = viewModel
        val dialog = LoadingDialog(this)

        val pagerAdapter = TabFragmentAdapter(this)

        val pager = binding.viewPager
        pager.isNestedScrollingEnabled = true
        pager.adapter = pagerAdapter

        val sharedPref = getSharedPreferences("strong", Context.MODE_PRIVATE)
        val id = sharedPref.getString("ID","")
        val token = sharedPref.getString("jwt_token","")
        val key = sharedPref.getString("key","")
        Log.d("kkkkkkkkkkkkkkkkkkkk",id!!)
        Log.d("kkkkkkkkkkkkkkkkkkkk",token!!)
        Log.d("kkkkkkkkkkkkkkkkkkkk",key!!)


        viewModel.getData(id!!, token!!, key!!)

        val tab = binding.tabLayout1
        TabLayoutMediator(tab, pager, {tab, position -> tab.text = tabTitles[position]}).attach()


        val PaymentBtn : ImageView = binding.imgPayment
        val TimeCoinBtn : ImageView = binding.imgTimecoin
        val SwapBtn : ImageView = binding.imgSwap

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
        viewModel.loading.observe(this, Observer {
            if(it)
                dialog.show()
            else
                dialog.dismiss()
        })

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
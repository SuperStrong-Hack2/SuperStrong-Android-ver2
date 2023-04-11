package com.superstrong.android.viewmodel

import Balance
import History
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LogVModel : ViewModel(){
    var balance = MutableLiveData<Balance>(Balance("0","0","0"))
    var historyList = mutableListOf<History>(History("보내기", "doge", "1.0","23-01-01","0xsfdsfs"))

    var loading = MutableLiveData<Boolean>(false)

    fun getData(){
        loading.value = true
        viewModelScope.launch {
            var job1 = async { delay(5000) }
            var job2 = async { delay(5000) }
            job1.await()
            job2.await()
            loading.value = false
        }
    }

}
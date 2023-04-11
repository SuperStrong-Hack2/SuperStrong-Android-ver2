package com.superstrong.android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.superstrong.android.data.Balance
import com.superstrong.android.data.History
import com.superstrong.android.data.Id
import com.superstrong.android.data.WalletRepo
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LogVModel : ViewModel(){
    var balance = MutableLiveData<Balance>(Balance(0,0.0,0.0,0.0))
    var historyList = mutableListOf<History>(History(0, 0, 1.0,"23-01-01","0xsfdsfs"))
    var repo = WalletRepo()
    var loading = MutableLiveData<Boolean>(false)

    fun getData(id:String, token:String, key:String){
        loading.value = true
        viewModelScope.launch {
            var job1 = async { repo.getBalance(Id(id), token, key)}
            var job2 = async { delay(5000) }
            val bal = job1.await()
            job2.await()
            if(bal == null){
                balance.value = Balance(0,-1.0,-1.0,-1.0)
            }
            else
                balance.value = bal!!
            historyList.clear()
            historyList.addAll(listOf(History(0, 0, 1.0,"23-01-01","0xsfdsfs"),
                History(0, 0, 1.0,"23-01-01","0xsfdsfs")))
            loading.value = false
        }
    }

}
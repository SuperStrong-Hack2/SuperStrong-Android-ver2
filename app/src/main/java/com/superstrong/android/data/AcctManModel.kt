package com.superstrong.android.data

import androidx.lifecycle.MutableLiveData

class AcctManModel(_jwt:String) {
    var jwt = _jwt
    fun checkPassword(pass:String) : Boolean {
        return pass == "1234"
    }
    fun checkSame(pass1:String, pass2:String):Boolean{
        return pass1 == pass2
    }
    fun newpass(pass1: String, pass2: String):Boolean{
        return true
    }
}
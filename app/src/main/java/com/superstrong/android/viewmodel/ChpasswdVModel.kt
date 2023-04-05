package com.superstrong.android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.superstrong.android.data.AcctManModel


class ChpasswdVModel() : ViewModel(){
    var jwt = "fgdfgdg"
    var wrongPasswd = MutableLiveData<Boolean>(false)
    var model = AcctManModel(jwt)
    var stage = MutableLiveData<Int>(1)
    var samePass = MutableLiveData<Boolean>(false)
    var done = MutableLiveData<Boolean>(false)

    fun back(){
        stage.value = stage.value!! - 1
    }
    fun checkCurrentPassword(pass:String){
        val ret = model.checkPassword(pass)
        wrongPasswd.value = !ret
        if(ret)
            stage.value=2
    }
    fun newpass(pass1:String, pass2:String){
        samePass.value = (pass1 != pass2)
        if(pass1 == pass2){
            var ret = model.newpass(pass1, pass2)
            if(ret)
                stage.value=3
        }
    }
    fun relogin(){
        done.value=true
    }
}
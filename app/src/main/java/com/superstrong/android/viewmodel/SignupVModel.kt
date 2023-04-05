package com.superstrong.android.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.superstrong.android.data.SignupModel

class SignupVModel : ViewModel() {
    var model = SignupModel()
    var stage = MutableLiveData<Int>(1) //fragment number
    var done = MutableLiveData<Boolean>(false) //signup done
    var unchecked = MutableLiveData<Boolean>(false) //unchecked term
    var error_code = MutableLiveData<Int>(1) //
    // 1 no error
    // 2 ID is already exist
    // 3 passwords not same
    // 4 email authentication is not done
    // 5 empty input

    var authfail = MutableLiveData<Int>(0)

    fun back(){
        stage.value = stage.value!! - 1
    }

    fun relogin(){ //go to login page
        done.value=true
    }
    fun all_checked(check1:Boolean, check2: Boolean){ //is all checked?
        unchecked.value= !check1 || !check2
        if(unchecked.value == false)
            stage.value=2
    }

    fun signupRequst(id:String, pass1:String, pass2:String, mail:String, jumin:String, phone:String){
        Log.d("aaaaaaaaaaaa",id)
        Log.d("aaaaaaaaaaaa",pass1)
        Log.d("aaaaaaaaaaaa",pass2)
        Log.d("aaaaaaaaaaaa",mail)
        Log.d("aaaaaaaaaaaa",jumin)
        Log.d("aaaaaaaaaaaa",phone)

        if(id =="" || pass1 == "" || pass2 == "" || mail == "" || jumin == "" || phone == "")
            error_code.value=5
        else {
            error_code.value = model.signupRequst(id, pass1, pass2, mail, jumin, phone)
            if (error_code.value == 1)
                stage.value = 3
        }
    }

    fun authRequest(authCode:String)
    {
        if(model.authCheck(authCode) == 0) {
            authfail.value = authfail.value!! + 1
        }
        else
        {
            authfail.value = 0
            stage.value = 4
        }
    }

}
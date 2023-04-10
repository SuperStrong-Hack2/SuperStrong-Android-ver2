package com.superstrong.android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.superstrong.android.data.*
import kotlinx.coroutines.launch


class SignupVModel : ViewModel() {
    var stage = MutableLiveData<Int>(1) //fragment number
    var done = MutableLiveData<Boolean>(false) //signup done
    var unchecked = MutableLiveData<Boolean>(false) //unchecked term
    var error_code = MutableLiveData<Int>(0) //
    var loading = MutableLiveData<Boolean>(false)
    val repo = Repository()
    var myId :String? = null

    // 0 good
    // 1 ID is already exist
    // 2 email already exist
    // 3 phone same
    // 4 ssn same
    // 5 not auth
    // 6 emty field
    // 7 pass not same
    // 8 connect error

    var auth_fail = MutableLiveData<Int>(1)
    // 1 이메일 인증 성공
    // 0 인증 실패
    // -1 통신 에러

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
    fun signupRequest(id:String, pass:String, mail:String, jumin:String, phone:String){
        val body = SignUpRequestBody(id,pass, mail, jumin,phone)
        loading.value = true
        viewModelScope.launch{
            val res = repo.signupRequest(body).data
            error_code.value = res?.result?.toInt()?: 8
            loading.value = false
            if(error_code.value == 0)
                stage.value = 3
        }
    }

    fun sendCode(code:String){
        val body = AuthCode(code)
        loading.value = true
        viewModelScope.launch{
            val res = repo.sendCode(body).data
            auth_fail.value = res?.result?.toInt() ?: -1
            myId = res?.id
            loading.value = false
            if(auth_fail.value == 1)
                stage.value = 4
        }
    }

    fun signupRequst(id:String, pass1:String, pass2:String, mail:String, ssn:String, phone:String){

        if(id =="" || pass1 == "" || pass2 == "" || mail == "" || ssn == "" || phone == "")
            error_code.value=6
        else if(pass1 != pass2)
            error_code.value=7
        else {
            signupRequest(id,pass1, mail,ssn,phone)
        }
    }

}
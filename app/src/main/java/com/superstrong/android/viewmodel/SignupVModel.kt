package com.superstrong.android.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.superstrong.android.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class SignupVModel : ViewModel() {
    var stage = MutableLiveData<Int>(1) //fragment number
    var done = MutableLiveData<Boolean>(false) //signup done
    var unchecked = MutableLiveData<Boolean>(false) //unchecked term
    var error_code = MutableLiveData<Int>(0) //
    var loading = MutableLiveData<Boolean>(false)
    val repo = Repository()
    var myId = ""
    var myKey = ""
    var pubAd = ""

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
        //loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val response = repo.signupRequest(body)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.d("ffffffffffffffffffffff","tttttttttttttttttttttttt")
                    error_code.value = response.body()!!.result.toInt()
                    if(error_code.value == 0)
                    {
                        myId = id
                        stage.value = 3
                    }
                } else {
                    error_code.value = 8
                }
               // loading.value = false
            }
        }
    }

    fun sendCode(code:String){
        val body = authCode(code)
        loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val response = repo.sendCode(body)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    loading.value = false
                    auth_fail.value = response.body()!!.result.toInt()
                    if(auth_fail.value == 1) {
                        pubAd = response.body()!!.pubAddress
                        Log.d("ssssssss",pubAd)
                        myKey = response.body()!!.key
                        Log.d("ffffffffffffffffffffff",myKey)
                        myId =  response.body()!!.id
                        Log.d("ffffffffffffffffffffff",myId)
                        stage.value = 4
                    }
                } else {
                    loading.value = false
                    auth_fail.value = -1
                }

            }
        }
    }

    fun signupRequst(id:String, pass1:String, pass2:String, mail:String, jumin:String, phone:String){

        if(id =="" || pass1 == "" || pass2 == "" || mail == "" || jumin == "" || phone == "")
            error_code.value=6
        else if(pass1 != pass2)
            error_code.value=7
        else {
            signupRequest(id,pass1, mail,jumin,phone)
        }
    }

}
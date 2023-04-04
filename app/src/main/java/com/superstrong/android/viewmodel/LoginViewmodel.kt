package com.superstrong.android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    val loginSuccess = MutableLiveData<Boolean>()
    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    fun onLoginClick() {
        // 로그인 버튼 클릭 시 실행할 코드 작성
        // 로그인 성공 시
        loginSuccess.postValue(true)
    }
}
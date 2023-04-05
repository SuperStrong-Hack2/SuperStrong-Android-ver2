package com.superstrong.android.data

class SignupModel {
    var myid:String =""
    fun signupRequst(id:String, pass1:String, pass2:String, mail:String, jumin:String, phone:String):Int{
        if(id == "admin")
            return 2
        else if(pass1 != pass2)
            return 3
        else if(id == "testid")
            return 4
        else {
            myid=id
            return 1
        }
        //return 1// 정상
        //return 2 // 아이디 중복
        //return 3 // 비밀번호확인 불일치
        //return 4 // 인증 대기중인 유저
    }
    fun authCheck(authcode:String):Int{
        if(authcode == "123456")
            return 1
        else
            return 0
    }
}
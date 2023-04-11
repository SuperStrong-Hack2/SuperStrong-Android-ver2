package com.superstrong.android.data

import com.google.gson.annotations.SerializedName

class PaymentModel {
}

data class PayInfo1( //POST
    @SerializedName("to_address")
    val to_address: String,
    @SerializedName("send_amount")
    val send_amount: Double,
    @SerializedName("coin_name")
    val coin_name: String,
    @SerializedName("token")
    var token: String
)

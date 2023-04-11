import com.google.gson.annotations.SerializedName

data class Balance(
    val btc:String,
    val eth:String,
    val doge:String
)

data class History(
    val type :String,
    val coin : String,
    val amount : String,
    val date : String,
    val address: String
)
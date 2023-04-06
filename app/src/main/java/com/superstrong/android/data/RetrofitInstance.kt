import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.superstrong.android.data.BackendApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://localhost:8080/" // 백엔드의 기본 URL을 입력해주세요

    val backendApiService: BackendApiService by lazy {
        val gson: Gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        retrofit.create(backendApiService::class.java)
    }
}
package com.superstrong.android.data

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import java.io.ByteArrayInputStream
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import javax.net.ssl.*


public interface BackendApiService {
    @POST("api/login")
    fun login(@Body encryptedData: EncryptedData): Call<JsonObject>

    // Payment1 -> Payment2 넘어갈 때 POST
    @POST("api/send_input")
    fun payment1(@Body encryptedData: EncryptedPayment): Call<JsonObject>

    //Payment1 -> Payment2 넘어갈 때 GET
    @POST("api/send_input")
    fun paymentRsp1(@Body payInfoRsp1: PayInfoRsp1): Call<JsonObject>

    // Payment2 -> Payment3 넘어갈 때 POST
    @POST("api/send")
    fun payment2(@Body encryptedData: EncryptedPayment): Call<JsonObject>


    @POST("api/register")
    @Headers("Content-Type: application/json")
    suspend fun signUp(@Body body: E2eReq): Response<E2eRes>

    /*@POST("api/auth")
    @Headers("Content-Type: application/json")
    fun emailAuth(@Body body: authCode): Call<UserData>*/

    @POST("api/register/auth")
    @Headers("Content-Type: application/json")
    suspend fun emailAuth(@Body body: E2eReq): Response<E2eRes>

    @POST("api/chpass")
    @Headers("Content-Type: application/json")
    suspend fun chPass(@Body body: Password): Response<ChpassReponse>

    @POST("api/chpass/newPass")
    @Headers("Content-Type: application/json")
    suspend fun newPass(@Body body: Password): Response<ChpassReponse>

    @POST("api/main_asset")
    @Headers("Content-Type: application/json")
    suspend fun getBalance(@Body body: E2eReq2): Response<E2eRes>

    @POST("/api/main_history")
    @Headers("Content-Type: application/json")
    suspend fun getHistory(@Body body: E2eReq2): Response<E2eRes>
}

class SingleCertTrustManager(private val cert: X509Certificate) : X509TrustManager {

    override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
        // Do nothing
    }

    override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
        chain?.let {
            Log.d("ddddddddddddddddddddddddddddddd",cert.toString())
            Log.d("ddddddddddddddddddddddddddddddd",it[0].toString())
            if (it.size == 1 && it[0] == cert) {
                Log.d("sssssssssssssssssssssssss","same same same smae")
                return
            }
        }
        throw CertificateException("Invalid server certificate")
    }

    override fun getAcceptedIssuers(): Array<X509Certificate> {
        return arrayOf(cert)
    }
}

object RetrofitInstance {
    private const val BASE_URL = "https://ec2-43-201-205-63.ap-northeast-2.compute.amazonaws.com"
    private val gson: Gson = GsonBuilder().create()


    private const val certString = "-----BEGIN CERTIFICATE-----\n" +
            "MIIDeTCCAmGgAwIBAgIIdIYm2L66sOMwDQYJKoZIhvcNAQELBQAwazELMAkGA1UE\n" +
            "BhMCa28xEDAOBgNVBAgTB1Vua25vd24xEDAOBgNVBAcTB1Vua25vd24xFDASBgNV\n" +
            "BAoTC3N1cGVyc3Ryb25nMRAwDgYDVQQLEwdVbmtub3duMRAwDgYDVQQDEwdVbmtu\n" +
            "b3duMB4XDTIzMDQxOTEzNTk0NFoXDTMzMDQxNjEzNTk0NFowazELMAkGA1UEBhMC\n" +
            "a28xEDAOBgNVBAgTB1Vua25vd24xEDAOBgNVBAcTB1Vua25vd24xFDASBgNVBAoT\n" +
            "C3N1cGVyc3Ryb25nMRAwDgYDVQQLEwdVbmtub3duMRAwDgYDVQQDEwdVbmtub3du\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwgQs4XHHZFSolBAlv7tL\n" +
            "mUsnnZZOaGQjgNaB/ct15ZHYoHP3LvP5FnbZlR+l4TK1VUixDeixT7LVS2hSZfrH\n" +
            "4HuD5QhmxDE/KabQU5YUUnaS3glNQAmPxLRuBcEigYv8vwQv14mdFxNrnq9fSHQE\n" +
            "tFedv0wDIU0ggKSZVGj3xUni8oIeJNi55IqBViePwUsAvn5y8ml1lMoKndeO8wmD\n" +
            "VN9VeMyqA7WTxavzRizW1IBOAFB+3xP8LdDFXhm+TmFJHkVlxebKMo7BZOiwQGpX\n" +
            "q9Th4QSCVb/GGmRhBM3miyY1cvU8ygBibzulo/6U34IuLrkBUXX+g45r2A4trpdd\n" +
            "WwIDAQABoyEwHzAdBgNVHQ4EFgQUmH4CjxH2V8mMsJHbMP3yYsNb22owDQYJKoZI\n" +
            "hvcNAQELBQADggEBACC9+u/hYr8VaIlNymVaNihJQoBSNF9lIj211Zx0FG6GN2Ze\n" +
            "RSkJ2gnAyli9h+tihpv7TeagJGi+9cpKDHu/t/Rn22zxYVFAPge5fXTk5ETICL3g\n" +
            "sJV9CoJWYUVgRY6FaQ77o5KlUx786hajzNolTEwuAQk6QHHgrdFph2nUzhcMccSl\n" +
            "/REckWUXcvykp2I5gVDvZN2bvdyUMdj/+coz79etr1pb1Ha8L1pEbl2r/8vCl4Wn\n" +
            "4HuuMUfOwponyrl3X9UWIdo6e/Z1oQqojHfpx8J2xI1rEWAc45QdOWde2PcB3xk+\n" +
            "7bxR2zyaXHzON7xfXvGDBkpwt6wCxUXMJqUi2Jo=\n" +
            "-----END CERTIFICATE-----"

    private val certInputStream = ByteArrayInputStream(certString.toByteArray())

    lateinit var certFactory: CertificateFactory
    lateinit var cert: X509Certificate
    lateinit var trustManager: X509TrustManager
    lateinit var sslContext: SSLContext
    lateinit var client: OkHttpClient
    lateinit var retrofit: Retrofit
    lateinit var backendApiService: BackendApiService
    init {
        certFactory = CertificateFactory.getInstance("X.509")
        cert = certFactory.generateCertificate(certInputStream) as X509Certificate



        trustManager = SingleCertTrustManager(cert)
        sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, arrayOf(trustManager), SecureRandom())

        client = OkHttpClient.Builder()
            .sslSocketFactory(sslContext.socketFactory, trustManager)
            .hostnameVerifier { _, _ -> true }
            .build()



        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()

        backendApiService = retrofit.create(BackendApiService::class.java)
        }
    }

/*object RetrofitInstance {
    private const val BASE_URL = "https://ec2-43-201-205-63.ap-northeast-2.compute.amazonaws.com"
    private val gson: Gson = GsonBuilder().create()

    lateinit var  certificatePinner: CertificatePinner
    lateinit var  sslSocketFactory: SSLSocketFactory
    lateinit var  okHttpClient: OkHttpClient


    private lateinit var client :OkHttpClient
    lateinit var  retrofit : Retrofit
    lateinit var backendApiService: BackendApiService



    class TrustAllCerts : X509TrustManager {
        override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
        }

        override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
        }

        override fun getAcceptedIssuers(): Array<X509Certificate> {
            return arrayOf()
        }
    }
    init{

        certificatePinner = CertificatePinner.Builder()
            .add("ec2-43-201-205-63.ap-northeast-2.compute.amazonaws.com", "sha256/RjIwOEFBOUI2NDYwQUU2RDY3NEI4QTgyQUM3MTg5MDEwNjcyMjJEQjc0N0YzODFBMTNGODE1NjFGNTNGM0FBMA==")
            .build()

        sslSocketFactory = SSLContext.getDefault().socketFactory

        okHttpClient = OkHttpClient.Builder()
            .certificatePinner(certificatePinner)
            .sslSocketFactory(sslSocketFactory, TrustAllCerts())
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        backendApiService = retrofit.create(BackendApiService::class.java)
    }
}*/

class RetrofitApiService {

}


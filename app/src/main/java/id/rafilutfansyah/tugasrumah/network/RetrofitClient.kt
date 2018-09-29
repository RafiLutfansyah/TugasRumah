package id.rafilutfansyah.tugasrumah.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Rafi Lutfansyah on 12/02/2018.
 */

class RetrofitClient {
    companion object {
        private var retrofit: Retrofit? = null
        val BASE_URL_API = "http://192.168.43.123/tugas-rumah/"

        fun getClient(): BaseApiService{
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL_API)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build()
            }

            return retrofit!!.create(BaseApiService::class.java)
        }
    }
}
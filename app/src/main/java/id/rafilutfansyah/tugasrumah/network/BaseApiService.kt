package id.rafilutfansyah.tugasrumah.network

import id.rafilutfansyah.tugasrumah.model.Soal
import id.rafilutfansyah.tugasrumah.model.Tugas
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Rafi Lutfansyah on 12/02/2018.
 */

interface BaseApiService {
    @GET("Siswa")
    fun checkDataSiswa(@Query("email") email: String): Call<Int>

    @GET("Tugas")
    fun getDaftarTugas(@Query("email") email: String): Call<List<Tugas>>

    @GET("Soal")
    fun getSoal(@Query("id_materi") idMateri: Int): Call<List<Soal>>

    @FormUrlEncoded
    @PUT("Tugas")
    fun setJawaban(@Field("id") id: Int,
                   @Field("hasil") hasil: String): Call<List<Tugas>>
}
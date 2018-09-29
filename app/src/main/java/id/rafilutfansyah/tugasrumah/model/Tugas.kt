package id.rafilutfansyah.tugasrumah.model

import android.content.Context
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.json.JSONObject

/**
 * Created by Rafi Lutfansyah on 19/03/2018.
 */

data class Tugas(
        @SerializedName("id") @Expose val id: Int,
        @SerializedName("email") @Expose val email: String,
        @SerializedName("username") @Expose val username: String,
        @SerializedName("id_materi") @Expose val idMateri: Int,
        @SerializedName("photo_url") @Expose val photoUrl: String,
        @SerializedName("tanggal_belajar") @Expose val tanggalBelajar: String,
        @SerializedName("tanggal_dikerjakan") @Expose val tanggalDikerjakan: String,
        @SerializedName("hasil") @Expose val hasil: String,
        @SerializedName("materi") @Expose val materi: String,
        @SerializedName("nama") @Expose val nama: String,
        @SerializedName("password") @Expose val password: String)
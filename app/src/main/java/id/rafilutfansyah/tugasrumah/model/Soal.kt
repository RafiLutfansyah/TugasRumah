package id.rafilutfansyah.tugasrumah.model

import android.content.Context
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.json.JSONArray
import org.json.JSONObject


/**
 * Created by Rafi Lutfansyah on 20/03/2018.
 */

data class Soal(@SerializedName("id") @Expose val id: Int,
                @SerializedName("id_materi") @Expose val idMateri: Int,
                @SerializedName("soal") @Expose val soal: String,
                @SerializedName("jawaban_benar") @Expose val jawabanBenar: String,
                @SerializedName("jawaban_salah1") @Expose val jawabanSalah1: String,
                @SerializedName("jawaban_salah2") @Expose val jawabanSalah2: String,
                @SerializedName("jawaban_salah3") @Expose val jawabanSalah3: String) {

    companion object {
        fun setJawaban(context: Context, nomorSoal: Int, id: Int, soal: String, jawaban: String, hasil: String) {
            val sharedPref = context.getSharedPreferences("jawaban", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()

            val json = JSONObject()
            json.put("id", id)
            json.put("soal", soal)
            json.put("jawaban", jawaban)
            json.put("hasil", hasil)

            editor.putString("$nomorSoal", json.toString())
            editor.commit()
        }

        fun getJawaban(context: Context, nomorSoal: Int): JSONObject? {
            val sharedPref = context.getSharedPreferences("jawaban", Context.MODE_PRIVATE)
            val jsonObject = sharedPref.getString("$nomorSoal", "null")
            return if(jsonObject != "null") {
                JSONObject(jsonObject)
            } else {
                null
            }
        }

        fun clearJawaban(context: Context) {
            val sharedPref = context.getSharedPreferences("jawaban", Context.MODE_PRIVATE)
            sharedPref.edit().clear().commit()
        }
    }
}
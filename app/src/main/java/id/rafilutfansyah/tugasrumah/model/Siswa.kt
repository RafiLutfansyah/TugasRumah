package id.rafilutfansyah.tugasrumah.model

import android.content.Context
import android.net.Uri
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Rafi Lutfansyah on 12/02/2018.
 */

data class Siswa(
        @SerializedName("email") @Expose val email: String,
        @SerializedName("nama") @Expose val nama: String /* ,
        @SerializedName("created_at") @Expose val createdAt: String,
        @SerializedName("updated_at") @Expose val updatedAt: String */ ) {

    companion object {
        fun saveSharedPref(context: Context,
                           nama: String,
                           email: String,
                           photoUrl: Uri /*,
                           createdAt: String,
                           updatedAt: String */ ) {
            val sharedPref = context.getSharedPreferences("data_mahasiswa", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("nama", nama)
            editor.putString("email", email)
            editor.putString("photo_url", photoUrl.toString())
            /* editor.putString("created_at", createdAt)
            editor.putString("updated_at", updatedAt) */
            editor.commit()
        }

        fun getNama(context: Context): String {
            val sharedPref = context.getSharedPreferences("data_mahasiswa", Context.MODE_PRIVATE)
            val nama = sharedPref.getString("nama", "null")
            return nama
        }

        fun getEmail(context: Context): String {
            val sharedPref = context.getSharedPreferences("data_mahasiswa", Context.MODE_PRIVATE)
            val email = sharedPref.getString("email", "null")
            return email
        }

        fun getPhotoUrl(context: Context) : String {
            val sharedPref = context.getSharedPreferences("data_mahasiswa", Context.MODE_PRIVATE)
            val photoUrl = sharedPref.getString("photo_url", "null")
            return photoUrl
        }

        /* fun getCreatedAt(context: Context): String {
            val sharedPref = context.getSharedPreferences("data_mahasiswa", Context.MODE_PRIVATE)
            val createdAt = sharedPref.getString("created_at", "null")
            return createdAt
        }

        fun getUpdatedAt(context: Context): String {
            val sharedPref = context.getSharedPreferences("data_mahasiswa", Context.MODE_PRIVATE)
            val updatedAt = sharedPref.getString("updated_at", "null")
            return updatedAt
        } */
    }
}
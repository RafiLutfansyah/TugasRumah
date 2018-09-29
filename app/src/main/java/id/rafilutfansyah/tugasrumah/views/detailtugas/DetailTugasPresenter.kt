package id.rafilutfansyah.tugasrumah.views.detailtugas

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import id.rafilutfansyah.tugasrumah.base.Presenter
import id.rafilutfansyah.tugasrumah.model.Soal
import id.rafilutfansyah.tugasrumah.model.Tugas
import id.rafilutfansyah.tugasrumah.network.RetrofitClient
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Rafi Lutfansyah on 15/02/2018.
 */

class DetailTugasPresenter(private val context: Context) : Presenter<DetailTugasView> {
    private var mView: DetailTugasView? = null
    private val email = FirebaseAuth.getInstance().currentUser!!.email
    private var mApiService = RetrofitClient.getClient()

    override fun onAttach(view: DetailTugasView) {
        mView = view
    }

    override fun onDetach() {
        mView = null
    }

    fun getSoal(idMateri: Int) {
        mApiService.getSoal(idMateri).enqueue(object : Callback<List<Soal>> {
            override fun onResponse(call: Call<List<Soal>>, response: Response<List<Soal>>) {
                Soal.clearJawaban(context)
                mView!!.onResponse(response.body()!!)
            }

            override fun onFailure(call: Call<List<Soal>>, t: Throwable) {
                mView!!.onFailure(t.toString())
            }
        })
    }

    fun setJawaban(id: Int, nomorSoal: Int) {
        val jsonArray = JSONArray()
        for (i in 1..nomorSoal) {
            val jsonObject = Soal.getJawaban(context, i)
            if (jsonObject != null) {
                jsonArray.put(jsonObject)
                if (i == nomorSoal) {
                    sendJawaban(id, jsonArray.toString())
                }
            } else {
                mView!!.soalBelumDijawab((i - 1))
                break
            }
        }
    }

    fun sendJawaban(id: Int, jsonArray: String) {
        mApiService.setJawaban(id, jsonArray).enqueue(object : Callback<List<Tugas>> {
            override fun onResponse(call: retrofit2.Call<List<Tugas>>, response: Response<List<Tugas>>) {
                mView!!.onSuccess(response.body()!![0])
            }

            override fun onFailure(call: retrofit2.Call<List<Tugas>>?, t: Throwable?) {
                mView!!.onFailure(t.toString())
            }

        })
    }
}
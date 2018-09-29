package id.rafilutfansyah.tugasrumah.views.daftartugas

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import id.rafilutfansyah.tugasrumah.base.Presenter
import id.rafilutfansyah.tugasrumah.model.Tugas
import id.rafilutfansyah.tugasrumah.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Rafi Lutfansyah on 15/02/2018.
 */

class DaftarTugasPresenter(context: Context) : Presenter<DaftarTugasView> {
    private var mView: DaftarTugasView? = null
    private val email = FirebaseAuth.getInstance().currentUser!!.email
    private var mApiService = RetrofitClient.getClient()

    override fun onAttach(view: DaftarTugasView) {
        mView = view
    }

    override fun onDetach() {
        mView = null
    }

    fun getDaftarTugas() {
        mApiService.getDaftarTugas(email!!).enqueue(object : Callback<List<Tugas>>{
            override fun onResponse(call: Call<List<Tugas>>, response: Response<List<Tugas>>) {
                mView!!.onResponse(DaftarTugasAdapter(response.body()!!))
            }

            override fun onFailure(call: Call<List<Tugas>>, t: Throwable) {
                mView!!.onFailure(t.toString())
            }
        })
    }
}
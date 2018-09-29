package id.rafilutfansyah.tugasrumah.views.soaltugas

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import id.rafilutfansyah.tugasrumah.base.Presenter
import id.rafilutfansyah.tugasrumah.model.Soal
import id.rafilutfansyah.tugasrumah.model.Tugas
import id.rafilutfansyah.tugasrumah.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Rafi Lutfansyah on 15/02/2018.
 */

class SoalTugasPresenter(context: Context) : Presenter<SoalTugasView> {
    private var mView: SoalTugasView? = null
    private val email = FirebaseAuth.getInstance().currentUser!!.email
    private var mApiService = RetrofitClient.getClient()

    override fun onAttach(view: SoalTugasView) {
        mView = view
    }

    override fun onDetach() {
        mView = null
    }
}
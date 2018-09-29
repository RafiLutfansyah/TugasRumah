package id.rafilutfansyah.tugasrumah.views.daftartugas

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import id.rafilutfansyah.tugasrumah.R
import kotlinx.android.synthetic.main.activity_daftar_tugas.*

class DaftarTugasActivity : AppCompatActivity(), DaftarTugasView {
    var presenter: DaftarTugasPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_tugas)
        initPresenter()
        onAttachView()
    }

    private fun initPresenter() {
        presenter = DaftarTugasPresenter(this)
    }

    override fun onAttachView() {
        presenter!!.onAttach(this)
        listRaport.setHasFixedSize(true)
        listRaport.layoutManager = LinearLayoutManager(this)
        presenter!!.getDaftarTugas()
    }

    override fun onDetachView() {
        presenter!!.onDetach()
    }

    override fun onDestroy() {
        onDetachView()
        super.onDestroy()
    }

    override fun onResponse(daftarTugasAdapter: DaftarTugasAdapter) {
        listRaport.adapter = daftarTugasAdapter
    }

    override fun onFailure(t: String) {
        Toast.makeText(this, t, Toast.LENGTH_SHORT).show()
    }
}

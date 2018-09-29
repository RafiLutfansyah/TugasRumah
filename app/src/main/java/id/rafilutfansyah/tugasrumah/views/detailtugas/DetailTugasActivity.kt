package id.rafilutfansyah.tugasrumah.views.detailtugas

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import id.rafilutfansyah.tugasrumah.R
import kotlinx.android.synthetic.main.activity_detail_tugas.*
import android.graphics.Color
import android.support.design.widget.TabLayout
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.rafilutfansyah.tugasrumah.adapter.ViewPagerAdapter
import id.rafilutfansyah.tugasrumah.model.Soal
import id.rafilutfansyah.tugasrumah.model.Tugas
import id.rafilutfansyah.tugasrumah.views.soaltugas.SoalTugasFragment
import kotlinx.android.synthetic.main.activity_soal_tugas.*
import org.json.JSONArray

class DetailTugasActivity : AppCompatActivity(), DetailTugasView {
    var presenter: DetailTugasPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tugas)
        initPresenter()
        onAttachView()
    }

    private fun initPresenter() {
        presenter = DetailTugasPresenter(this)
    }

    override fun onAttachView() {
        presenter!!.onAttach(this)

        Glide.with(this)
                .load("${resources.getString(R.string.BASE_URL)}uploads/${intent.getStringExtra("photo_url")}")
                .into(imageFoto)
        collapsingToolbar.title = intent.getStringExtra("materi")
        collapsingToolbar.setExpandedTitleColor(Color.parseColor("#75ffffff"))

        textTanggal.text = intent.getStringExtra("tanggal_belajar")
        textTrainer.text = intent.getStringExtra("nama")

        if (intent.getStringExtra("tanggal_dikerjakan") == null || intent.getStringExtra("hasil") == null) {
            textTanggalDikerjakan.visibility = View.GONE
            textHasil.visibility = View.GONE
            buttonKerjakan.setOnClickListener {
                presenter!!.getSoal(intent.getStringExtra("id_materi").toInt())
            }
        } else {
            textTanggalDikerjakan.text = intent.getStringExtra("tanggal_dikerjakan")
            textHasil.text = jsonToString(intent.getStringExtra("hasil"))
            buttonKerjakan.visibility = View.GONE
        }
    }

    override fun onDetachView() {
        presenter!!.onDetach()
    }

    override fun onDestroy() {
        onDetachView()
        super.onDestroy()
    }

    override fun onResponse(soals: List<Soal>) {
        setContentView(R.layout.activity_soal_tugas)
        var nomorSoal = 0
        val adapter = ViewPagerAdapter(supportFragmentManager)
        soals.forEach { soal ->
            val fragment = SoalTugasFragment()
            val data = Bundle()
            data.putInt("nomorSoal", ++nomorSoal)
            data.putInt("id", soal.id)
            data.putInt("idMateri", soal.idMateri)
            data.putString("soal", soal.soal)
            data.putString("jawabanBenar", soal.jawabanBenar)
            data.putString("jawabanSalah1", soal.jawabanSalah1)
            data.putString("jawabanSalah2", soal.jawabanSalah2)
            data.putString("jawabanSalah3", soal.jawabanSalah3)

            fragment.arguments = data
            adapter.addFragment(fragment, "$nomorSoal")
        }
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = (soals.size - 1)
        tabs.setupWithViewPager(viewPager)
        fabSoal.setOnClickListener {
            presenter!!.setJawaban(Integer.parseInt(intent.extras["id"].toString()), nomorSoal)
        }
    }

    override fun onFailure(t: String) {
        Toast.makeText(this, t, Toast.LENGTH_SHORT).show()
    }

    override fun soalBelumDijawab(nomorSoal: Int) {
        viewPager.currentItem = nomorSoal
    }

    override fun onSuccess(tugas: Tugas) {
        val intent = Intent(this, DetailTugasActivity::class.java)
        intent.putExtra("id", tugas.id)
        intent.putExtra("email", tugas.email)
        intent.putExtra("username", tugas.username)
        intent.putExtra("id_materi", tugas.idMateri.toString())
        intent.putExtra("tanggal_belajar", tugas.tanggalBelajar)
        intent.putExtra("tanggal_dikerjakan", tugas.tanggalDikerjakan)
        intent.putExtra("photo_url", tugas.photoUrl)
        intent.putExtra("hasil", tugas.hasil)
        intent.putExtra("materi", tugas.materi)
        intent.putExtra("nama", tugas.nama)
        intent.putExtra("password", tugas.password)
        startActivity(intent)
        finish()
    }

    private fun jsonToString(json: String): String {
        val jsonArray = JSONArray(json)
        val string = StringBuilder()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val soal = jsonObject.getString("soal")
            val jawaban = jsonObject.getString("jawaban")
            val hasil = jsonObject.getString("hasil")
            string.append("""
                No. ${i + 1}
                Soal: $soal
                Jawaban: $jawaban
                Hasil: $hasil

            """.trimIndent())
        }

        return string.toString()
    }
}

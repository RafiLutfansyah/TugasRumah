package id.rafilutfansyah.tugasrumah.views.daftartugas

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.rafilutfansyah.tugasrumah.model.Tugas
import java.text.SimpleDateFormat
import id.rafilutfansyah.tugasrumah.R
import id.rafilutfansyah.tugasrumah.views.detailtugas.DetailTugasActivity
import kotlinx.android.synthetic.main.adapter_daftar_tugas.view.*
import java.util.*

/**
 * Created by Rafi Lutfansyah on 13/02/2018.
 */

class DaftarTugasAdapter(private val tugases: List<Tugas>) : RecyclerView.Adapter<DaftarTugasAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(tugas: Tugas) = with(itemView) {


            Glide.with(this)
                    .load("${resources.getString(R.string.BASE_URL)}uploads/${tugas.photoUrl}")
                    .apply(RequestOptions().override(600, 200).centerCrop())
                    .into(imageFoto)

            val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(tugas.tanggalBelajar)
            val cal = GregorianCalendar()
            cal.time = date
            var hari = cal.get(Calendar.DAY_OF_WEEK).toString()
            when (hari) {
                "1" -> hari = "Senin"
                "2" -> hari = "Selasa"
                "3" -> hari = "Rabu"
                "4" -> hari = "Kamis"
                "5" -> hari = "Jumat"
                "6" -> hari = "Sabtu"
                "7" -> hari = "Minggu"
            }
            val tanggal = tugas.tanggalBelajar.substring(8, 10)
            val bulan = tugas.tanggalBelajar.substring(4, 8)
            val tahun = tugas.tanggalBelajar.substring(0, 4)
            val jamMenit = tugas.tanggalBelajar.substring(11, 16)

            itemView.textTanggalBelajar.text = "$hari, $tanggal$bulan$tahun $jamMenit"
            itemView.textNamaMateri.text = tugas.materi

            itemView.setOnClickListener {
                val intent = Intent(context, DetailTugasActivity::class.java)
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
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_daftar_tugas, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(tugases[position])
    }

    override fun getItemCount(): Int {
        return tugases.size
    }
}
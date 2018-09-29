package id.rafilutfansyah.tugasrumah.views.detailtugas

import id.rafilutfansyah.tugasrumah.base.View
import id.rafilutfansyah.tugasrumah.model.Soal
import id.rafilutfansyah.tugasrumah.model.Tugas

/**
 * Created by Rafi Lutfansyah on 15/02/2018.
 */

interface DetailTugasView : View {
    fun onResponse(soals: List<Soal>)
    fun onFailure(t: String)
    fun soalBelumDijawab(nomorSoal: Int)
    fun onSuccess(tugas: Tugas)
}
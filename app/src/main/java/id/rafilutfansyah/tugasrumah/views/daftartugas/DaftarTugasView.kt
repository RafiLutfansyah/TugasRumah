package id.rafilutfansyah.tugasrumah.views.daftartugas

import id.rafilutfansyah.tugasrumah.base.View

/**
 * Created by Rafi Lutfansyah on 15/02/2018.
 */

interface DaftarTugasView : View {
    fun onResponse(daftarTugasAdapter: DaftarTugasAdapter)
    fun onFailure(t: String)
}
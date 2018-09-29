package id.rafilutfansyah.tugasrumah.views.soaltugas

import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.rafilutfansyah.tugasrumah.R
import id.rafilutfansyah.tugasrumah.model.Soal
import java.util.*
import android.content.Context
import android.util.Log
import kotlinx.android.synthetic.main.fragment_soal_tugas.*

/**
 * Created by Rafi Lutfansyah on 13/02/2018.
 */

class SoalTugasFragment : Fragment(), SoalTugasView {
    var presenter: SoalTugasPresenter? = null

    var nomorSoal: Int? = null
    var id: Int? = null
    var idMateri: Int? = null
    var soal: String? = null
    var jawabanBenar: String? = null
    var jawabanSalah1: String? = null
    var jawabanSalah2: String? = null
    var jawabanSalah3: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("FRAGMENT", "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("FRAGMENT", "onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_soal_tugas, container, false)
        Log.d("FRAGMENT", "onCreateView")
        
        nomorSoal = arguments!!.getInt("nomorSoal")
        id = arguments!!.getInt("id")
        idMateri = arguments!!.getInt("idMateri")
        soal = arguments!!.getString("soal")
        jawabanBenar = arguments!!.getString("jawabanBenar")
        jawabanSalah1 = arguments!!.getString("jawabanSalah1")
        jawabanSalah2 = arguments!!.getString("jawabanSalah2")
        jawabanSalah3 = arguments!!.getString("jawabanSalah3")

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("FRAGMENT", "onViewCreated");
    }

    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("FRAGMENT", "onActivityCreated")

        initPresenter()
        onAttachView()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("FRAGMENT", "onViewStateRestored")
    }

    override fun onStart() {
        super.onStart()
        Log.d("FRAGMENT", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("FRAGMENT", "onResume")
    }

    override fun onPause() {
        Log.d("FRAGMENT", "onPause")
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("FRAGMENT", "onSaveInstanceState")
    }

    override fun onStop() {
        Log.d("FRAGMENT", "onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d("FRAGMENT", "onDestroyView")
        onDetachView()
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("FRAGMENT", "onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d("FRAGMENT", "onDetach")
        super.onDetach()
    }

    private fun initPresenter() {
        Log.d("FRAGMENT", "initPresenter")
        presenter = SoalTugasPresenter(activity!!)
    }

    override fun onAttachView() {
        Log.d("FRAGMENT", "onAttachView")
        presenter!!.onAttach(this)

        textSoal.text = soal

        val jawaban = listOf(
                arrayOf(jawabanBenar!!, "benar"),
                arrayOf(jawabanSalah1!!, "salah"),
                arrayOf(jawabanSalah2!!, "salah"),
                arrayOf(jawabanSalah3!!, "salah"))

        Collections.shuffle(jawaban)

        buttonA.text = jawaban[0][0]
        buttonB.text = jawaban[1][0]
        buttonC.text = jawaban[2][0]
        buttonD.text = jawaban[3][0]

        buttonA.setBackgroundResource(android.R.drawable.btn_default);
        buttonB.setBackgroundResource(android.R.drawable.btn_default);
        buttonC.setBackgroundResource(android.R.drawable.btn_default);
        buttonD.setBackgroundResource(android.R.drawable.btn_default);

        buttonA.setOnClickListener {
            buttonA.setBackgroundResource(R.color.colorPrimary)
            buttonB.setBackgroundResource(android.R.drawable.btn_default);
            buttonC.setBackgroundResource(android.R.drawable.btn_default);
            buttonD.setBackgroundResource(android.R.drawable.btn_default);

            Soal.setJawaban(context!!, nomorSoal!!, id!!, soal!!, jawaban[0][0], jawaban[0][1])
            Log.d("JSON Object", "$nomorSoal. A. ${Soal.getJawaban(context!!, nomorSoal!!).toString()}")
        }

        buttonB.setOnClickListener {
            buttonB.setBackgroundResource(R.color.colorPrimary)
            buttonA.setBackgroundResource(android.R.drawable.btn_default);
            buttonC.setBackgroundResource(android.R.drawable.btn_default);
            buttonD.setBackgroundResource(android.R.drawable.btn_default);

            Soal.setJawaban(context!!, nomorSoal!!, id!!, soal!!, jawaban[1][0], jawaban[1][1])
        }

        buttonC.setOnClickListener {
            buttonC.setBackgroundResource(R.color.colorPrimary)
            buttonA.setBackgroundResource(android.R.drawable.btn_default);
            buttonB.setBackgroundResource(android.R.drawable.btn_default);
            buttonD.setBackgroundResource(android.R.drawable.btn_default);

            Soal.setJawaban(context!!, nomorSoal!!, id!!, soal!!, jawaban[2][0], jawaban[2][1])
        }

        buttonD.setOnClickListener {
            buttonD.setBackgroundResource(R.color.colorPrimary)
            buttonA.setBackgroundResource(android.R.drawable.btn_default);
            buttonB.setBackgroundResource(android.R.drawable.btn_default);
            buttonC.setBackgroundResource(android.R.drawable.btn_default);

            Soal.setJawaban(context!!, nomorSoal!!, id!!, soal!!, jawaban[3][0], jawaban[3][1])
        }

    }

    override fun onDetachView() {
        Log.d("FRAGMENT", "onDetachView")
        presenter!!.onDetach()
    }
}
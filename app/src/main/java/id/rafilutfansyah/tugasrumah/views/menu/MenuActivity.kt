package id.rafilutfansyah.tugasrumah.views.menu

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import id.rafilutfansyah.tugasrumah.R
import id.rafilutfansyah.tugasrumah.views.TentangActivity
import id.rafilutfansyah.tugasrumah.views.daftartugas.DaftarTugasActivity
import id.rafilutfansyah.tugasrumah.views.login.LoginActivity
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity(), MenuView {
    var presenter: MenuPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        initPresenter()
        onAttachView()
    }

    private fun initPresenter() {
        presenter = MenuPresenter(this)
    }

    override fun onAttachView() {
        presenter!!.onAttach(this)
        presenter!!.checkCurrentUser()
        buttonDaftarTugas.setOnClickListener {
            startActivity(Intent(this, DaftarTugasActivity::class.java))
        }
        buttonTentang.setOnClickListener {
            startActivity(Intent(this, TentangActivity::class.java))
        }
        buttonLogout.setOnClickListener {
            presenter!!.signOut()
        }
    }

    override fun onDetachView() {
        presenter!!.onDetach()
    }

    override fun onDestroy() {
        onDetachView()
        super.onDestroy()
    }

    override fun onShowCurrentUser(name: String, email: String, photoUrl: String) {
        Glide.with(this)
                .load(photoUrl)
                .into(imageFoto)
        textNama.text = name
        textEmail.text = email
    }

    override fun onShowLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
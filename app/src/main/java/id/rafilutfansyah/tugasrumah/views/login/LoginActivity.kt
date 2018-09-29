package id.rafilutfansyah.tugasrumah.views.login

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import id.rafilutfansyah.tugasrumah.R
import id.rafilutfansyah.tugasrumah.views.menu.MenuActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {
    var presenter: LoginPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initPresenter()
        onAttachView()
    }

    private fun initPresenter() {
        presenter = LoginPresenter(this)
    }

    override fun onAttachView() {
        presenter!!.onAttach(this)
        signInButton.setOnClickListener {
            presenter!!.signIn()
            barLoading.visibility = View.VISIBLE
            signInButton.isEnabled = false
        }
    }

    override fun onDetachView() {
        presenter!!.onDetach()
    }

    override fun onDestroy() {
        onDetachView()
        super.onDestroy()
    }

    override fun signIn(signInIntent: Intent, RC_SIGN_IN: Int) {
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter!!.onActivityResult(requestCode, data!!)
    }

    override fun onActivityResultSuccess() {
        Toast.makeText(this, "Google Sign In was successful, authenticate with Firebase", Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResultError() {
        Toast.makeText(this, "Google sign in failed", Toast.LENGTH_SHORT).show()
        barLoading.visibility = View.INVISIBLE
        signInButton.isEnabled = true
    }

    override fun signInWithCredentialSuccess() {
        Toast.makeText(this, "signInWithCredential:success", Toast.LENGTH_SHORT).show()
    }

    override fun signInWithCredentialError() {
        Toast.makeText(this, "Authentication Failed.", Toast.LENGTH_SHORT).show()
        barLoading.visibility = View.INVISIBLE
        signInButton.isEnabled = true
    }

    override fun emailTerdaftar() {
        startActivity(Intent(this, MenuActivity::class.java))
        finish()
    }

    override fun emailTidakTerdaftar() {
        Toast.makeText(this, "Email Tidak Terdaftar", Toast.LENGTH_SHORT).show()
    }

    override fun onFailure(t: String) {
        Toast.makeText(this, t, Toast.LENGTH_SHORT).show()
        barLoading.visibility = View.INVISIBLE
        signInButton.isEnabled = true
    }
}
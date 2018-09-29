package id.rafilutfansyah.tugasrumah.views.menu

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import id.rafilutfansyah.tugasrumah.base.Presenter
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import id.rafilutfansyah.tugasrumah.R

class MenuPresenter(context: Context) : Presenter<MenuView> {
    private var mView: MenuView? = null
    private val mAuth = FirebaseAuth.getInstance()
    private val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    private val mGoogleSignInClient: GoogleSignInClient? = GoogleSignIn.getClient(context, gso)

    override fun onAttach(view: MenuView) {
        mView = view
    }

    override fun onDetach() {
        mView = null
    }

    fun checkCurrentUser() {
        val mUser = mAuth.currentUser
        if (mUser != null) {
            mView!!.onShowCurrentUser(mUser.displayName!!, mUser.email!!, mUser.photoUrl.toString())
        } else {
            mView!!.onShowLogin()
        }
    }

    fun signOut() {
        mAuth.signOut()
        mGoogleSignInClient!!.signOut().addOnCompleteListener {
            mView!!.onShowLogin()
        }
    }
}
package id.rafilutfansyah.tugasrumah.views.login

import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import id.rafilutfansyah.tugasrumah.base.Presenter
import id.rafilutfansyah.tugasrumah.network.RetrofitClient
import com.google.firebase.auth.FirebaseUser
import id.rafilutfansyah.tugasrumah.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Rafi Lutfansyah on 16/02/2018.
 */

class LoginPresenter(val context: Context) : Presenter<LoginView> {
    private var mView: LoginView? = null
    private val TAG = "GoogleActivity"
    private val RC_SIGN_IN = 9001
    private val mAuth = FirebaseAuth.getInstance()
    private val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    private val mGoogleSignInClient: GoogleSignInClient? = GoogleSignIn.getClient(context, gso)
    private var mApiService = RetrofitClient.getClient()

    override fun onAttach(view: LoginView) {
        mView = view
    }

    override fun onDetach() {
        mView = null
    }

    fun signIn() {
        val signInIntent = mGoogleSignInClient!!.signInIntent
        mView!!.signIn(signInIntent, RC_SIGN_IN)
    }

    fun onActivityResult(requestCode: Int, data: Intent) {
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
                mView!!.onActivityResultSuccess()
            } catch (e: ApiException) {
                Log.w(TAG, "Google sign in failed", e)
                mView!!.onActivityResultError()
            }
        }
    }

    fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.id)

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "signInWithCredential:success")
                        val user = mAuth.currentUser
                        if (user != null) {
                            checkDataSiswa(user)
                        }
                    } else {
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                        mView!!.signInWithCredentialError()
                    }
                }
    }

    private fun checkDataSiswa(user: FirebaseUser){
        mApiService.checkDataSiswa(user.email!!).enqueue(object : Callback<Int>{
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                if(response.body()!! > 0) {
                    mView!!.emailTerdaftar()
                } else {
                    mView!!.emailTidakTerdaftar()
                }
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                mView!!.onFailure(t.toString())
            }
        })
    }
}
package com.qwlyz.androidstudy.fragment

import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.graphics.Color
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.blankj.utilcode.util.ColorUtils
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.qwlyz.androidstudy.BaseFragment
import com.qwlyz.androidstudy.R
import com.qwlyz.androidstudy.databinding.FragmentGoogleBinding
import com.qwlyz.androidstudy.pp.BitmapPalette
import com.qwlyz.androidstudy.pp.GlidePalette
import com.yuwq.libs_common.viewBinding


/**
 *
 * @author lyz
 */
class GoogleLoginFragment2 : BaseFragment() {

    companion object{
        lateinit var oneTapClient: SignInClient
        lateinit var mAuth: FirebaseAuth
    }

    private lateinit var signInRequest: BeginSignInRequest
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var signInClient: SignInClient
    val serverKey = "561818321660-a3odr025oe92kgs4nv1m8hjahj6ats0r.apps.googleusercontent.com"
    private val binding by viewBinding(FragmentGoogleBinding::bind)

    override fun getLayoutId(): Int = com.qwlyz.androidstudy.R.layout.fragment_google

    override fun initData() {
        mAuth = FirebaseAuth.getInstance()
        signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(serverKey)
                    .setFilterByAuthorizedAccounts(true)
                    .build()
            )
            .build()

        signInClient = getOneTapClient(requireActivity())


        binding.login.setOnClickListener {
            signInClient.beginSignIn(signInRequest).addOnSuccessListener {
                Log.d(TAG, "addOnSuccessListener: ${it.pendingIntent}")
                try {
                    ActivityCompat.startIntentSenderForResult(
                        requireActivity(),
                        it.pendingIntent.intentSender, REQ_ONE_TAP,
                        null, 0, 0, 0, null
                    )
                } catch (e: IntentSender.SendIntentException) {
                    Log.e(
                        TAG,
                        "oneTapSignIn, SendIntentException, e:${e.localizedMessage}"
                    )
                }
            }.addOnFailureListener {
                Log.d(TAG, "addOnFailureListener: ${it.message}")
            }
        }

    }

    private fun getOneTapClient(activity: Activity): SignInClient {
        oneTapClient = Identity.getSignInClient(activity)
        return oneTapClient
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        mAuth.signOut()
//        mGoogleSignInClient.signOut()
    }

    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.getSignInIntent()
        startActivityForResult(signInIntent, 123123)
    }

    // ...
    private val REQ_ONE_TAP = 2  // Can be any integer unique to the Activity
    private var showOneTapUI = true
    // ...

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQ_ONE_TAP -> {
                try {
                    val credential = oneTapClient.getSignInCredentialFromIntent(data)
                    val idToken = credential.googleIdToken
                    when {
                        idToken != null -> {
                            // Got an ID token from Google. Use it to authenticate
                            // with Firebase.
                            Log.d("liuyuzhe", "Got ID token.")
                        }

                        else -> {
                            // Shouldn't happen.
                            Log.d("liuyuzhe", "No ID token!")
                        }
                    }
                } catch (e: ApiException) {
                    Log.d("liuyuzhe", "No ID token!: ${e.message}")
                }
                // ...
            }
        }
    }

    fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        Log.d(TAG, "handleSignInResult: ${task.result.idToken}")

//        val credential = GoogleAuthProvider.getCredential(task.result.idToken, null)
//        mAuth.signInWithCredential(credential).addOnCompleteListener {
//                if (it.isSuccessful){
//                    Log.d(TAG, "firebase success")
//                }else{
//                    Log.d(TAG, "firebase fail: ${it.exception?.message}")
//                }
//            }

    }
}


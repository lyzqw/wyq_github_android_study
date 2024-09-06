package com.qwlyz.androidstudy

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import com.google.firebase.auth.GoogleAuthProvider
import com.qwlyz.androidstudy.fragment.GoogleLoginFragment2

class WidgetActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context, widget: PageWidget) {
            val starter = Intent(context, WidgetActivity::class.java)
                .putExtra("PageWidget", widget)
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widget)
        val pageWidget = intent.getSerializableExtra("PageWidget") as PageWidget
        val baseFragment = pageWidget.pageWidgetClass.newInstance() as BaseFragment
        findViewById<TextView>(R.id.title_view).text = pageWidget.title
        findViewById<TextView>(R.id.title_view).visibility =
            if (pageWidget.title.isNullOrEmpty()) View.GONE else View.VISIBLE
        baseFragment.arguments = Bundle().also { it.putSerializable("PageWidget", pageWidget) }
        supportFragmentManager.beginTransaction().replace(R.id.container, baseFragment)
            .commitNowAllowingStateLoss()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        Log.d("StorageFragment", "onRequestPermissionsResult: " + grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val credential = GoogleLoginFragment2.oneTapClient.getSignInCredentialFromIntent(data)
        val idToken = credential.googleIdToken
        when {
            idToken != null -> {
                // Got an ID token from Google. Use it to authenticate
                // with Firebase.
                android.util.Log.d("liuyuzhe", "Got ID token.: $idToken")
                val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
                GoogleLoginFragment2.mAuth.signInWithCredential(firebaseCredential)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user = GoogleLoginFragment2.mAuth.currentUser
                            Log.d(TAG, "signInWithCredential:success . : $user")
//                            updateUI(user)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.exception)
//                            updateUI(null)
                        }
                    }



            }

            else -> {
                // Shouldn't happen.
                android.util.Log.d("liuyuzhe", "No ID token!")
            }
        }
    }

}
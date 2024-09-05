package com.qwlyz.androidstudy.fragment

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ColorUtils
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
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
class GoogleLoginFragment : BaseFragment() {

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var mAuth: FirebaseAuth
    private val binding by viewBinding(FragmentGoogleBinding::bind)

    override fun getLayoutId(): Int = com.qwlyz.androidstudy.R.layout.fragment_google

    override fun initData() {
//        val url  = "https://heartoai.oss-us-east-1.aliyuncs.com/management/2024-08-12/1723452199950.png"
//        val url = "https://heartoai.oss-us-east-1.aliyuncs.com/management/2024-08-12/1723434805267.jpeg"
        val url = "https://heartoai.oss-us-east-1.aliyuncs.com/management/2024-08-12/1723461429514.jpeg"
//        val url = "https://heartoai.oss-us-east-1.aliyuncs.com/management/2024-08-12/1723452351785.jpeg"

//        Glide.with(requireActivity())
//            .load(url)
//            .listener(GlidePalette.with(url)
//                .use(BitmapPalette.Profile.MUTED_LIGHT)
//                .intoCallBack { palette ->
//                    val dominantColor = palette?.getDominantColor(ColorUtils.getColor(R.color.Blue)) ?: ColorUtils.getColor(R.color.Blue)
//                    // 使用 dominantColor
//                    binding.text.setTextColor(dominantColor)
//                }
//            )
//            .into(binding.image)



//        Glide.with(this).load(url)
//            .listener(GlidePalette.with(url)
//                .use(BitmapPalette.Profile.MUTED_LIGHT)
//                .intoTextColor(titleView, GlidePalette.Swatch.BODY_TEXT_COLOR)
//                .crossfade(true)
//            );
//        .into(imageView);

        Glide.with(requireView())
            .load(url)
            .listener(
                GlidePalette.with(url)
                    .use(BitmapPalette.Profile.MUTED_LIGHT)
                    .intoCallBack { palette ->
                        val light = palette?.lightVibrantSwatch?.rgb
                        val domain = palette?.dominantSwatch?.rgb
                        light?.let { binding.text.setTextColor(it) }
                        domain?.let { binding.text2.setTextColor(it) }
//                        binding.text2.setTextColor(Color.parseColor("#ff2830"))
                        if (domain != null) {
                            if (light != null) {
//                                Rainbow(paletteView).palette {
//                                    +color(domain)
//                                    +color(light)
//                                }.background(orientation = RainbowOrientation.TOP_BOTTOM)
                            } else {
//                                paletteView.setBackgroundColor(domain)
                            }
                            if (context is AppCompatActivity) {
                                requireActivity().window.apply {
                                    addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                                    statusBarColor = domain
                                }
                            }
                        }
                    }.crossfade(true),
            ).into(binding.image)



        // 初始化 FirebaseAuth
        mAuth = FirebaseAuth.getInstance()


        // 配置 Google Sign-In 选项
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("561818321660-a3odr025oe92kgs4nv1m8hjahj6ats0r.apps.googleusercontent.com")
            .requestEmail()
            .build()


        // 创建 Google Sign-In 客户端
        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)


        binding.login.setOnClickListener {
            signIn()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mAuth.signOut()
        mGoogleSignInClient.signOut()
    }

    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.getSignInIntent()
        startActivityForResult(signInIntent, 123123)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Google 登录响应
        if (requestCode == 123123) {
            val task  = GoogleSignIn.getSignedInAccountFromIntent(data)

            handleSignInResult(task)
        }
    }

    fun handleSignInResult(task: Task<GoogleSignInAccount>){
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


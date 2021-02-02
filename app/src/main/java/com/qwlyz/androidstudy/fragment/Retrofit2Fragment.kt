package com.qwlyz.androidstudy.fragment

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.graphics.BitmapFactory
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.FileIOUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.qwlyz.androidstudy.BaseFragment
import com.qwlyz.androidstudy.R
import com.qwlyz.androidstudy.SimpleActivity
import kotlinx.android.synthetic.main.fragment_start_activity.*
import kotlinx.android.synthetic.main.fragment_storage.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.io.File

/**
 *
 * @author lyz
 */
class Retrofit2Fragment : BaseFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_retrofit

    override fun initData() {
        start.setOnClickListener {

            val retrofit = Retrofit.Builder()
                .baseUrl("http://wanandroid.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(Service::class.java).list()
                .enqueue(object : Callback<WQQAny> {
                    override fun onFailure(call: Call<WQQAny>, t: Throwable) {
                        LogUtils.d("error: ${t.message}")
                    }

                    override fun onResponse(call: Call<WQQAny>, response: Response<WQQAny>) {
                        LogUtils.d("success: $response")
                    }
                })
        }
    }


    interface Service {

        @GET("/list")
        fun list(): Call<WQQAny>
    }

    class WQQAny : Any()
}


//package com.qwlyz.androidstudy.fragment
//
//import com.blankj.utilcode.util.LogUtils
//import com.qwlyz.androidstudy.BaseFragment
//import com.qwlyz.androidstudy.R
//import kotlinx.android.synthetic.main.fragment_start_activity.*
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.http.GET
//
///**
// *
// * @author lyz
// */
//class Retrofit2Fragment : BaseFragment() {
//
//    override fun getLayoutId(): Int = R.layout.fragment_retrofit
//
//    override fun initData() {
//        start.setOnClickListener {
//
//            val retrofit = Retrofit.Builder()
//                .baseUrl("http://wanandroid.com")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//
//            retrofit.create(Service::class.java).list()
//                .enqueue(object : Callback<WQQAny> {
//                    override fun onFailure(call: Call<WQQAny>, t: Throwable) {
//                        LogUtils.d("error: ${t.message}")
//                    }
//
//                    override fun onResponse(call: Call<WQQAny>, response: Response<WQQAny>) {
//                        LogUtils.d("success: $response")
//                    }
//                })
//        }
//    }
//
//
//    interface Service {
//
//        @GET("/list")
//        fun list(): Call<WQQAny>
//    }
//
//    class WQQAny : Any()
//}
//

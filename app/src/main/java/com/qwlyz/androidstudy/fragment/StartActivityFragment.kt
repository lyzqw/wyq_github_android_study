//package com.qwlyz.androidstudy.fragment
//
//import android.Manifest
//import android.content.ContentValues
//import android.content.pm.PackageManager.PERMISSION_GRANTED
//import android.graphics.BitmapFactory
//import android.os.Build
//import android.provider.MediaStore
//import android.util.Log
//import androidx.annotation.RequiresApi
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import com.blankj.utilcode.util.FileIOUtils
//import com.blankj.utilcode.util.Utils
//import com.qwlyz.androidstudy.BaseFragment
//import com.qwlyz.androidstudy.R
//import com.qwlyz.androidstudy.SimpleActivity
//import kotlinx.android.synthetic.main.fragment_start_activity.*
//import kotlinx.android.synthetic.main.fragment_storage.*
//import java.io.File
//
///**
// *
// * @author lyz
// */
//class StartActivityFragment : BaseFragment() {
//
//    override fun getLayoutId(): Int = R.layout.fragment_start_activity
//
//    override fun initData() {
//        start.setOnClickListener {
//            SimpleActivity.start(activity!!)
//        }
//    }
//
//}
//

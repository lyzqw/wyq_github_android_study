package com.qwlyz.androidstudy.fragment

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.graphics.BitmapFactory
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.FileIOUtils
import com.blankj.utilcode.util.Utils
import com.qwlyz.androidstudy.BaseFragment
import com.qwlyz.androidstudy.R
import kotlinx.android.synthetic.main.fragment_storage.*
import java.io.File

/**
 *
 * @author lyz
 */
class Android11StorageFragment : BaseFragment() {

    var readPath =
        "storage/emulated/0/DCIM/Screenshots/Screenshot_2020-12-17-19-36-57-439_com.vpb.popo.png"
    var writePath =
        "storage/emulated/0/DCIM/Screenshots/test_file.txt"
    var PERMISSION_CODE = 12

    override fun getLayoutId(): Int = R.layout.fragment_storage

    override fun initData() {
        request.setOnClickListener {
            val permissions = arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
                            , Manifest.permission.ACCESS_MEDIA_LOCATION
            )
            permissions.forEach {
                if (ContextCompat.checkSelfPermission(
                        Utils.getApp(),
                        it
                    ) != PERMISSION_GRANTED
                ) {
                    val activity = activity!!
                    ActivityCompat.requestPermissions(activity, permissions, PERMISSION_CODE);
                }
            }

        }

        write.setOnClickListener {
            Log.d(TAG, "initData: write")
            val writeFile = File(writePath)
            if (!writeFile.exists()) {
                writeFile.createNewFile()
            }
            FileIOUtils.writeFileFromString(writeFile, "haha")
            Log.d(TAG, "initData: " + FileIOUtils.readFile2String(writeFile))
        }

        read.setOnClickListener {
            Log.d(TAG, "initData: read")
            val file = File(readPath)
            image.setImageBitmap(BitmapFactory.decodeFile(file.absolutePath))
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        Log.d(TAG, "onRequestPermissionsResult: " + grantResults)
    }

}


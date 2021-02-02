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
import com.blankj.utilcode.util.Utils
import com.qwlyz.androidstudy.BaseFragment
import com.qwlyz.androidstudy.R
import com.qwlyz.androidstudy.SimpleActivity
import kotlinx.android.synthetic.main.fragment_storage.*
import java.io.File

/**
 *
 * @author lyz
 */
class Android11StorageFragment : BaseFragment() {

    var readPath =
        "/storage/emulated/0/Android/data/com.vpb.popo/cache/crop/16101598311.jpg"
    var writeImage =
        "storage/emulated/0/DCIM/Screenshots/Screenshot_2020-12-17-19-36-57-123_com.vpb.popo.png"
    var writePath = "storage/emulated/0/DCIM/Screenshots/test_file.txt"
    var PERMISSION_CODE = 12

    override fun getLayoutId(): Int = R.layout.fragment_storage

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun initData() {
        request.setOnClickListener {
            val permissions = arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
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

        read_9.setOnClickListener {
            Log.d(TAG, "initData: read")
            val file = File(readPath)
            image.setImageBitmap(BitmapFactory.decodeFile(file.absolutePath))
        }
        write_9.setOnClickListener {
            Log.d(TAG, "initData: write")
            val writeFile = File(writePath)
            if (!writeFile.exists()) {
                writeFile.createNewFile()
            }
            FileIOUtils.writeFileFromString(writeFile, "haha")
        }


        read_11.setOnClickListener {
            val file2 = File("/storage/emulated/0/Android/data/com.qwlyz.androidstudy/cache/ic_sex_radio_selected.png")
            Log.d(TAG, "filesDir:.. "+ file2)
            image.setImageBitmap(BitmapFactory.decodeFile(file2.absolutePath))
        }

        write_11.setOnClickListener {
            writeImage()
        }

        select_photo.setOnClickListener {}

        crop.setOnClickListener {
            SimpleActivity.start(activity!!)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        Log.d(TAG, "onRequestPermissionsResult: " + grantResults)
    }



    private fun writeImage() {
        val resolver = activity!!.contentResolver
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "friends.jpg")
        }

        val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        if (uri != null) {
            val outputStream = resolver.openOutputStream(uri)
            if (outputStream == null) {
                return
            }
            val inputStream = resources.assets.open("friends.png")
            val byteArray = ByteArray(1024)
            try {
                inputStream.use { input ->
                    outputStream.use { output ->
                        while (true) {
                            val readLen = input.read(byteArray)
                            if (readLen == -1) {
                                break
                            }
                            outputStream.write(byteArray, 0, readLen)
                        }
                    }
                }
            } catch (e: Throwable) {
                Log.e("wfeii", "mediaStore e:$e")
            }

        }
        Log.e("wfeii", "mediaStore:$uri?.toString()")
        image.setImageURI(uri)
    }
}


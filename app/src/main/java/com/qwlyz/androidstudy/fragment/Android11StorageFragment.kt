package com.qwlyz.androidstudy.fragment

import android.Manifest
import android.content.ContentUris
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.FileIOUtils
import com.blankj.utilcode.util.Utils
import com.qwlyz.androidstudy.BaseFragment
import com.qwlyz.androidstudy.R
import kotlinx.android.synthetic.main.fragment_storage.*
import java.io.File
import java.io.FileInputStream

/**
 *
 * @author lyz
 */
class Android11StorageFragment : BaseFragment() {

    var readPath =
        "storage/emulated/0/DCIM/Screenshots/Screenshot_2020-12-17-19-36-57-439_com.vpb.popo.png"
    var writeImage =
        "storage/emulated/0/DCIM/Screenshots/Screenshot_2020-12-17-19-36-57-123_com.vpb.popo.png"
    var writePath = "storage/emulated/0/DCIM/Screenshots/test_file.txt"
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
            Log.d(TAG, "initData: write12")
            writeImage()
//            val writeFile = File(writePath)
//            if (!writeFile.exists()) {
//                writeFile.createNewFile()
//            }
//            FileIOUtils.writeFileFromString(writeFile, "haha")
//            Log.d(TAG, "initData: " + FileIOUtils.readFile2String(writeFile))
        }

        read.setOnClickListener {
            Log.d(TAG, "initData: read..")
//            val file = File(readPath)
//            image.setImageBitmap(BitmapFactory.decodeFile(file.absolutePath))
            readExternalFileByMediaStore()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        Log.d(TAG, "onRequestPermissionsResult: " + grantResults)
    }


    private fun readExternalFileByMediaStore() {
        var pathKey = ""
        var pathValue = ""
        pathKey = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            MediaStore.MediaColumns.DATA
        } else {
            MediaStore.MediaColumns.RELATIVE_PATH
        }
        // RELATIVE_PATH会在路径的最后自动添加/
        pathValue = readPath
        val cursor = activity!!.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,
            if (pathKey.isEmpty()) {
                null
            } else {
                "$pathKey LIKE ?"
            },
            if (pathValue.isEmpty()) {
                null
            } else {
                arrayOf("%$pathValue%")
            },
            "${MediaStore.MediaColumns.DATE_ADDED} desc"
        )

        cursor?.also {
            while (it.moveToNext()) {
                val id = it.getLong(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns._ID))
                val displayName = it.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DISPLAY_NAME))
                val uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
                Log.d("ScopedStorageActivity", "read external uri:$uri, name:$displayName")
                Toast.makeText(Utils.getApp(), "$displayName", Toast.LENGTH_LONG).show()
                image.setImageURI(uri)
            }
        }
        cursor?.close()
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


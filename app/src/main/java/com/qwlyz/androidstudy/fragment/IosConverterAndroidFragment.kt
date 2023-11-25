//package com.qwlyz.androidstudy.fragment
//
//import android.os.Environment
//import android.util.Log
//import com.blankj.utilcode.util.FileIOUtils
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
//import com.qwlyz.androidstudy.BaseFragment
//import com.qwlyz.androidstudy.R
//import kotlinx.android.synthetic.main.fragment_ios_converter_android.*
//import org.json.JSONObject
//import java.io.File
//import java.io.InputStreamReader
//import java.lang.reflect.Type
//
//
///**
// *
// * @author lyz
// */
//class IosConverterAndroidFragment : BaseFragment() {
//
//    val gson = Gson()
//    val disableList = ArrayList<String>()
//
//    companion object {
//        const val TAG = "wqq"
//    }
//
//    override fun getLayoutId(): Int = R.layout.fragment_ios_converter_android
//
//    override fun initData() {
//
//        converter.setOnClickListener {
//            val iosCnMap = readJson("ios_localizable-cn.json")
//            val iosArMap = readJson("ios_localizable-ar.json")
//            val androidCnMap = readJson("andriod_cn.json")
//            val androidArMap = readJson("android_ar.json")
//            Log.d(TAG, "Android 阿拉伯数量: " + androidArMap.size)
//            Log.d(TAG, "ios 阿拉伯数量: " + iosArMap.size)
//            assertMapValue(iosCnMap, androidCnMap)
//
//            var iosCn: String = ""
//            var iosAr: String = ""
//            for ((iosCnkey, iosValue) in iosCnMap) {
//                var result: Boolean = false
//                for ((iosArKey, iosArValue) in iosArMap) {
//                    if (iosCnkey == iosArKey) {
//                        iosCn = iosValue
//                        iosAr = iosArValue
//                        result = true
//                        break
//                    }
//                }
//                if (result) {
//                    changeSingleText(androidCnMap, iosCn, androidArMap, iosAr)
//                } else {
//                    Log.e(TAG, "找不到的内容" + iosCn)
//                }
//            }
//            writeJsonToSDcard(JSONObject(androidArMap as Map<*, *>).toString(),"android_ar.json")
//            writeJsonToSDcard(Gson().toJson(disableList).toString(),"不满足的文案.json")
//        }
//    }
//
//    private fun writeJsonToSDcard(json: String,fileName:String) {
//
//        val sdDir = Environment.getExternalStorageDirectory()
//        val sdpath: String = sdDir.absolutePath
//        val filepath = sdpath + File.separator.toString() + fileName
//        val result = FileIOUtils.writeFileFromString(filepath, json)
//        Log.d(
//            TAG,
//            "initData: 写入成功 result $result, 文件: ${File(filepath).exists()} , 路径 ${filepath}"
//        )
//    }
//
//
//    /**
//     * 判断Android集合里是否满足iOS的字段
//     */
//    private fun assertMapValue(
//        iosCnMap: MutableMap<String, String>,
//        androidCnMap: MutableMap<String, String>
//    ) {
//        for ((iosCnkey, iosValue) in iosCnMap) {
//            assertValue(iosValue, androidCnMap)
//        }
//    }
//
//    var awaitCount = 0
//    var changeCount = 0
//
//    private fun changeSingleText(
//        androidCnMap: MutableMap<String, String>,
//        iosCn: String,
//        androidArMap: MutableMap<String, String>,
//        iosAr: String
//    ) {
//
//        val ios = iosCn.replace(" ","")
//        for ((adCnKey, adCnValue) in androidCnMap) {
//            val android = adCnValue.replace(" ","")
//            if (ios == android) {
//                val awaitValue = androidArMap[adCnKey]
//                awaitCount++
//                Log.d(
//                    TAG,
//                    "可以修改的数据: $awaitCount 待修改的文案: awaitValue: $awaitValue => 他的中文: $adCnKey 正确的文案: $iosAr"
//                )
//                androidArMap[adCnKey] = iosAr
//                return
//            }
//        }
//    }
//
//    var ok_count = 0;
//    var dis_count = 0
//    private fun assertValue(iosCn: String, androidCnMap: MutableMap<String, String>) {
//        if (!androidCnMap.containsValue(iosCn)) {
//            dis_count++
//            Log.e(TAG, "这个文案不包含: " + iosCn)
//            Log.e(TAG, "不满足数量: " + dis_count)
//            disableList.add(iosCn)
//        } else {
//            ok_count++
////            Log.d(TAG, "assertValue: 满足数量" + ok_count + "; 总数: " + androidCnMap.size)
//        }
//    }
//
//    private fun readJson(jsonPath: String): MutableMap<String, String> {
//        val inputStream = context!!.resources.assets.open(jsonPath)
//
//        val type: Type = object :
//            TypeToken<Map<String?, String?>?>() {}.type
//        val myMap = gson.fromJson<Map<String, String>>(InputStreamReader(inputStream), type)
//
//        return myMap.toMutableMap()
//    }
//
//
//}
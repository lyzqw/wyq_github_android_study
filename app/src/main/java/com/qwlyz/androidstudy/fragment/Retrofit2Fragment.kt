package com.qwlyz.androidstudy.fragment

import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SizeUtils
import com.blankj.utilcode.util.StringUtils
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView
import com.qwlyz.androidstudy.BaseFragment
import com.qwlyz.androidstudy.R
import com.qwlyz.androidstudy.databinding.FragmentHorizontalViewPagerBinding
import com.qwlyz.androidstudy.databinding.FragmentRetrofitBinding
import com.yuwq.libs_common.viewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.io.IOException

/**
 *
 * @author lyz
 */
class Retrofit2Fragment : BaseFragment() {

    private val binding by viewBinding(FragmentRetrofitBinding::bind)


    override fun getLayoutId(): Int = R.layout.fragment_retrofit

    override fun initData() {
        binding.start.setOnClickListener {
            val retrofit = Retrofit
                .Builder().client(
                    OkHttpClient.Builder()
                        .addInterceptor(BlushCommonHeaderInterceptor())
                        .addInterceptor(BlushSSEInterceptor())
                        .build()
                )
                .baseUrl("http://wanandroid.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(Service::class.java).list()
                .enqueue(object : Callback<WQQAny> {
                    override fun onFailure(call: Call<WQQAny>, t: Throwable) {
                        LogUtils.d("error: ${t.message}")
                    }

                    override fun onResponse(call: Call<WQQAny>, response: Response<WQQAny>) {
                        LogUtils.d("success: ${call.request().url}")
                    }
                })
        }
    }

    class BlushSSEInterceptor : Interceptor {

        companion object {
            const val KEY_SENSITIVE_INVALID_CONTENT = "key_sensitive_invalid"
        }

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            val response: okhttp3.Response = chain.proceed(chain.request())
            Log.d(this.toString(), "response: $response")
            if (response.isSuccessful && response.body != null) {
                val responseBody = response.body
                val content = responseBody!!.string()
                val dataParts = content.split("data:".toRegex())
                val result = StringBuilder()
                for (part in dataParts) {
                    if (part.trim().isNotEmpty()) {
                        val outputContent = getContent(part)
                        if (KEY_SENSITIVE_INVALID_CONTENT == outputContent) {
                            result.append(KEY_SENSITIVE_INVALID_CONTENT)
                            break
                        }
                        result.append(outputContent)
                    }
                }
                return response.newBuilder()
                    .body(ResponseBody.create(responseBody.contentType(), GsonUtils.toJson(result)))
                    .build()
            }
            return response
        }

        private fun getContent(part: String): String {
            try {
                val jsonObject = JSONObject(part)
                val code = jsonObject.optInt("input_sensitive_type")
                if (code != 0) {
                    return KEY_SENSITIVE_INVALID_CONTENT
                } else {
                    return jsonObject.getString("content")
                }
            } catch (e: Exception) {
            }
            return ""
        }
    }


    class BlushCommonHeaderInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            var request = chain.request()
            val builder = request.newBuilder()
            val header = request.headers.newBuilder()
            header["Authorization"] = "1234"
            request = builder.headers(header.build()).build()
            return chain.proceed(request)
        }

    }

    interface Service {

        @GET("/list")
        fun list(): Call<WQQAny>

        @GET("/list")
        suspend fun list2(): Call<WQQAny>
    }

    class WQQAny : Any()
}


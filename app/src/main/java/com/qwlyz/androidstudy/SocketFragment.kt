package com.qwlyz.androidstudy

import android.util.Log
import android.view.View
import com.qwlyz.androidstudy.databinding.FragmentPermissionDialogBinding
import com.wq.glide.annotation.compiler.OnClick
import com.yuwq.libs_common.viewBinding
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import java.net.URL
import kotlin.coroutines.CoroutineContext

class SocketFragment : BaseFragment() {

    private lateinit var socket: WebSocket

    private val binding by viewBinding(FragmentPermissionDialogBinding::bind)

    override fun getLayoutId(): Int = R.layout.fragment_socket

    override fun initData() {

    }

    class EEE : CoroutineExceptionHandler{
        override val key: CoroutineContext.Key<*>
            get() = TODO("Not yet implemented")

        override fun handleException(context: CoroutineContext, exception: Throwable) {
            TODO("Not yet implemented")
        }

    }

    @OnClick(R.id.btnInit)
    fun onClick1(v: View) {
        Log.d(TAG, "onClick1: init")
        GlobalScope.launch(
            context = Dispatchers.Main + EEE(), start = CoroutineStart.DEFAULT) {
            val request = Request.Builder()
//            .url(URL("","",6666,""))
                .url("wss://socket.gxmanq.com:6666")
                .build()
            val builder = OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
            socket = builder.build().newWebSocket(request, object : WebSocketListener() {
                override fun onOpen(webSocket: WebSocket, response: Response) {
                    Log.d(TAG, "onOpen: $response")
                }

                override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                    Log.d(TAG, "onClosed.reason: $reason, code: $code")
                }

                override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                    Log.d(TAG, "onFailure.response: $response , Throwable: $t")
                }
            })
        }
    }

    @OnClick(R.id.btnSend)
    fun onClick2(v: View) {
        Log.d(TAG, "onClick1: send")
        socket.send("")
    }
}

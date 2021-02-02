package com.qwlyz.androidstudy

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SimpleActivity : AppCompatActivity() {

    companion object{
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, SimpleActivity::class.java)
            context.startActivity(starter)
            (context as Activity).overridePendingTransition(0, 0);
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
    }


    override fun finish() {
        super.finish()
        overridePendingTransition(0, 0);
    }
}
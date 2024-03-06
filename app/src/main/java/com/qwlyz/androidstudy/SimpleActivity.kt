package com.qwlyz.androidstudy

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.FragmentUtils
import com.qwlyz.androidstudy.fragment.LifeFragment1

class SimpleActivity : AppCompatActivity() {

    private val taskViewModel by viewModels<TViewModel>()

    var i = 1;

    companion object {

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
        Log.d(TAG_LIFE, "onCreate.${savedInstanceState}: ")

        findViewById<View>(R.id.load1).setOnClickListener {
//            FragmentUtils.add(supportFragmentManager, LifeFragment1(), R.id.container, false, false)
            supportFragmentManager.beginTransaction().replace(R.id.container, LifeFragment1())
                .commitNowAllowingStateLoss()
        }
//        taskViewModel.dataLiveData.observe(this){
//
//        }
    }


    override fun finish() {
        super.finish()
        overridePendingTransition(0, 0);
        Log.d(TAG_LIFE, "finish: ")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d(TAG_LIFE, "onNewIntent: ")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG_LIFE, "onSaveInstanceState:${outState} ")
        outState.putInt("state_i", i)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG_LIFE, "onRestoreInstanceState: ")
    }

//    override fun onRestoreInstanceState(
//        savedInstanceState: Bundle?,
//        persistentState: PersistableBundle?
//    ) {
//        super.onRestoreInstanceState(savedInstanceState, persistentState)
//    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        Log.d(TAG_LIFE, "onSaveInstanceState.outPersistentState: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG_LIFE, "onDestroy: ")
    }
}
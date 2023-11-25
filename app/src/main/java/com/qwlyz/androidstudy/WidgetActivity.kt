package com.qwlyz.androidstudy

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class WidgetActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context, widget: PageWidget) {
            val starter = Intent(context, WidgetActivity::class.java)
                .putExtra("PageWidget", widget)
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widget)
        val pageWidget = intent.getSerializableExtra("PageWidget") as PageWidget
        val baseFragment = pageWidget.pageWidgetClass.newInstance() as BaseFragment
        findViewById<TextView>(R.id.title_view).text = pageWidget.title
        baseFragment.arguments = Bundle().also { it.putSerializable("PageWidget", pageWidget) }
        supportFragmentManager.beginTransaction().replace(R.id.container, baseFragment).commitNowAllowingStateLoss()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        Log.d("StorageFragment", "onRequestPermissionsResult: "+grantResults)
    }
}
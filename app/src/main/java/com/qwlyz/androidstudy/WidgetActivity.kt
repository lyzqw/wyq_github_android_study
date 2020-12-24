package com.qwlyz.androidstudy

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_widget.*

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
        title_view.text = pageWidget.title
        baseFragment.arguments = Bundle().also { it.putSerializable("PageWidget", pageWidget) }
        supportFragmentManager.beginTransaction().replace(R.id.container, baseFragment).commitNowAllowingStateLoss()
    }
}
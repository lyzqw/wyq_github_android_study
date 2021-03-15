package com.qwlyz.androidstudy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.android36kr.app.module.common.log.KrLog
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_widget.view.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val instance = FirebaseAnalytics.getInstance(this)

        recycler_view.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recycler_view.adapter = WidgetAdapter(instance)
    }

    class WidgetAdapter(val instance: FirebaseAnalytics) : RecyclerView.Adapter<WidgetViewHolder>() {
        val dataList = PageWidget.values()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WidgetViewHolder {
            return WidgetViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_widget, null, false)
            )
        }

        override fun getItemCount(): Int = dataList.size

        override fun onBindViewHolder(holder: WidgetViewHolder, position: Int) {
            holder.itemView.text_view.text = dataList[position].title
            holder.itemView.setOnClickListener {
                WidgetActivity.start(holder.itemView.context, dataList[position])
            }
        }

    }

    class WidgetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onDestroy() {
        super.onDestroy()
        KrLog.close()
    }
}
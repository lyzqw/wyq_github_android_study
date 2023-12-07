package com.qwlyz.androidstudy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.qwlyz.androidstudy.databinding.ActivityMainBinding
import com.tencent.mars.xlog.Log
import com.yuwq.libs_common.viewBinding


class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        binding.recyclerView.adapter = WidgetAdapter()

        Log.i("MainActivity", Log.getSysInfo())
        Log.i("MainActivity", "ok")
        Log.i("MainActivity", "结束")
    }

    class WidgetAdapter : RecyclerView.Adapter<WidgetViewHolder>() {
        private val dataList = PageWidget.values()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WidgetViewHolder {
            return WidgetViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_widget, null, false)
            )
        }

        override fun getItemCount(): Int = dataList.size

        override fun onBindViewHolder(holder: WidgetViewHolder, position: Int) {
            holder.itemView.findViewById<TextView>(R.id.text_view).text = dataList[position].title
            holder.itemView.setOnClickListener {
                WidgetActivity.start(holder.itemView.context, dataList[position])
            }
        }

    }

    class WidgetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onDestroy() {
        super.onDestroy()
//        KrLog.close()
    }
}
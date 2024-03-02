package com.qwlyz.androidstudy.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.qwlyz.androidstudy.BaseFragment
import com.qwlyz.androidstudy.NBAStar
import com.qwlyz.androidstudy.R
import com.qwlyz.androidstudy.StickHeaderDecoration
import com.qwlyz.androidstudy.databinding.FragmentStickBinding
import com.yuwq.libs_common.viewBinding


/**
 *
 * @author lyz
 */
class StickFragment : BaseFragment() {

    private val binding by viewBinding(FragmentStickBinding::bind)

    override fun getLayoutId(): Int = R.layout.fragment_stick

    override fun initData() {
        val data = arrayListOf<NBAStar>()
//        for (i in 0..120) {
//            if (i == 0 || i == 2 || i == 12 || i == 22) {
//                data.add(NBAStar("詹姆斯：$i", "stick 头: $i"))
//            } else {
//                data.add(NBAStar("詹姆斯：$i", ""))
//            }
//        }

//        for (i in 0..5) {
//            data.add(NBAStar(String.format("第一组%d号", i + 1), ""))
//        }
//        for (i in 0..5) {
//            data.add(NBAStar(String.format("第二组%d号", i + 1), ""))
//        }
        for (i in 0..5) {
            data.add(NBAStar(String.format("第三组%d号", i + 1), ""))
        }
        for (i in 0..49) {
            data.add(NBAStar(String.format("第四组%d号", i + 1), "第四组"))
        }

        binding.rv.addItemDecoration(StickHeaderDecoration(context,binding.rv))
        binding.rv.layoutManager = LinearLayoutManager(context)
//        binding.rv.adapter = object :
//            BaseQuickAdapter<String, BaseViewHolder>(android.R.layout.simple_list_item_1, data) {
//            override fun convert(holder: BaseViewHolder, item: String) {
//                holder.setText(android.R.id.text1, item)
//            }
//        }

        binding.rv.adapter = NBAStarAdapter(data)
//        binding.rv.setOnTouchListener { v, event ->
//            Log.d("liuyuzhe", "initData: ")
//
//            return@setOnTouchListener false
//        }
    }


    class NBAStarAdapter(val dataList: List<NBAStar>) : RecyclerView.Adapter<NBAStarViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NBAStarViewHolder {

            return NBAStarViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_stick, parent, false)
            )
        }

        fun isGroupHeader(position: Int): Boolean {
            if (position == 0) return false

            val cName = getGroupName(position)
            val preName = getGroupName(position - 1)
            Log.d("Stick", "isGroupHeader..cName: $cName,preName: $preName")
            val header = (cName == preName).not()
            Log.d("Stick", "header: $header")
            return header
        }

        fun getGroupName(position: Int): String {
            return dataList[position].groupName
        }

        override fun getItemCount(): Int = dataList.size

        override fun onBindViewHolder(holder: NBAStarViewHolder, position: Int) {
//            if (isGroupHeader(position)){
//                return
//            }
            holder.view.findViewById<TextView>(R.id.text_view).text = dataList[position].name
        }


    }

    class NBAStarViewHolder(val view: View) : RecyclerView.ViewHolder(view)

}
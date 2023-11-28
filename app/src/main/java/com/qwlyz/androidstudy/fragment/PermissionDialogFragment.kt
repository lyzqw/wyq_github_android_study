package com.qwlyz.androidstudy.fragment

import android.Manifest
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.SizeUtils
import com.qwlyz.androidstudy.BaseFragment
import com.qwlyz.androidstudy.R
import com.qwlyz.androidstudy.databinding.FragmentPermissionDialogBinding
import com.yuwq.libs_common.viewBinding

/**
 *
 * @author lyz
 */
class PermissionDialogFragment : BaseFragment() {

    private val binding by viewBinding(FragmentPermissionDialogBinding::bind)

    override fun getLayoutId(): Int = R.layout.fragment_permission_dialog

    override fun initData() {
        binding.showDialog.setOnClickListener {

            val popupWindow = PopupWindow(context)

//            popView.visibility = View.GONE
//            popupWindow.showAsDropDown(target, 0, 0)

            PermissionUtils.permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .callback { isAllGranted, granted, deniedForever, denied ->
                    Log.d("Permission", "initData: $isAllGranted")
                    popupWindow.dismiss()
                }
                .request()

            val popView = layoutInflater.inflate(R.layout.layout_popowindow2, null)
            val layoutParams =
                ViewGroup.LayoutParams(SizeUtils.dp2px(200f), SizeUtils.dp2px(100f))
            popView.layoutParams = layoutParams
            popupWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            popupWindow.contentView = popView
            val target = requireActivity().window.decorView
            popupWindow.showAtLocation(target, Gravity.TOP, 0, 0)
        }

        binding.share.setOnClickListener {

            val popupWindow = PopupWindow(context)

//            popView.visibility = View.GONE
//            popupWindow.showAsDropDown(target, 0, 0)

            PermissionUtils.permission(Manifest.permission.CAMERA)
                .callback { isAllGranted, granted, deniedForever, denied ->
                    Log.d("Permission", "initData: $isAllGranted")
                    popupWindow.dismiss()
                }
                .request()

            val popView = layoutInflater.inflate(R.layout.layout_popowindow2, null)
            val layoutParams =
                ViewGroup.LayoutParams(SizeUtils.dp2px(200f), SizeUtils.dp2px(100f))
            popView.layoutParams = layoutParams
            popupWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            popupWindow.contentView = popView
            val target = requireActivity().window.decorView

            it.postDelayed({
                popupWindow.showAtLocation(target, Gravity.TOP, 0, 0)
            },1000)
        }
    }
}
package com.qwlyz.androidstudy.fragment

import android.graphics.drawable.Drawable
import android.util.Log
import com.android36kr.app.module.common.log.KrLog
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.qwlyz.androidstudy.BaseFragment
import com.qwlyz.androidstudy.R
import kotlinx.android.synthetic.main.fragment_glide_thumbnail.*


/**
 *
 * @author lyz
 */
class GlideImageFragment : BaseFragment() {

    override fun getLayoutId(): Int  = R.layout.fragment_glide_thumbnail

    override fun initData() {
        Glide.get(activity!!).clearMemory();
        Thread(){Glide.get(activity!!).clearDiskCache();}.start()
        load_image.setOnClickListener {
            Log.e("wqq", "initData: ")

            val thumbnailRequest = Glide.with(this)
//                .load("https://images.unsplash.com/photo-1497613913923-e07e0f465b12?dpr=2&auto=compress,format&fit=crop&w=376")
                .load("https://picsum.photos/50/50?image=0")

            Glide.with(this)
//                .load("https://picsum.photos/2000/2000?image=0")
                .load("https://images.unsplash.com/photo-1497613913923-e07e0f465b12")
                .listener(object :RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Log.e("wqq", "onResourceReady: ")
                        return false
                    }

                })
                .thumbnail(thumbnailRequest)
                .into(image_progress)

        }

    }

}
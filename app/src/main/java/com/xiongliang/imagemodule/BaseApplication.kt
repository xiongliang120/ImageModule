package com.xiongliang.imagemodule

import android.app.Application
import com.pingerx.imagego.core.ImageGo
import com.pingerx.imagego.glide.GlideImageStrategy

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initImageLoader()
    }

    /**
     * 初始化图片加载
     */
    fun initImageLoader(){
        ImageGo.setStrategy(GlideImageStrategy())
    }
}
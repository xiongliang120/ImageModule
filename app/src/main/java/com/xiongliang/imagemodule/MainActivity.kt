package com.xiongliang.imagemodule

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


class MainActivity : AppCompatActivity() {
    var imageView: ImageView? = null
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initData()

        ImageManager.loadImage();
    }


    fun initView() {
        imageView = findViewById(R.id.imageView)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun initData() {
        var url = "http://seopic.699pic.com/photo/50041/aaa.jpg"
//        loadImage(url, imageView, null, R.mipmap.ic_launcher, R.mipmap.ic_launcher, null)
        imageView?.let {
            Glide.with(it)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .load(url)
                .addListener(object : RequestListener<Bitmap?> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap?>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Log.i("xiongliang", "图片加载失败")
                        return false
                    }

                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap?>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Log.i(
                            "xiongliang",
                            "图片加载成功" + "宽=" + resource?.width + "高=" + resource?.height
                        )

                        return false
                    }
                })
                .into(it)
        }

        calucateSize()
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun calucateSize(){
        val options = BitmapFactory.Options()
        BitmapFactory.decodeResource(resources,R.drawable.aaa, options)

        //获取图片的宽高
        //获取图片的宽高
        val height = options.outHeight
        val width = options.outWidth

       Log.i("xiongliang","打印宽高="+width+".."+height)

        var bitmap = BitmapFactory.decodeResource(resources,R.drawable.aaa)
        Log.i("xiongliang","打印宽高="+bitmap.width+".."+bitmap.height)
    }
}

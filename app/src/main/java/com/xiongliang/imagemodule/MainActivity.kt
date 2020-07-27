package com.xiongliang.imagemodule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.pingerx.imagego.core.ImageGo
import com.pingerx.imagego.core.strategy.*

class MainActivity : AppCompatActivity() {
    var imageView: ImageView?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initData()
    }


    fun initView(){
        imageView = findViewById(R.id.imageView)
    }

    fun initData(){
       var url = "http://seopic.699pic.com/photo/50041/3365.jpg_wh1200.jpg"
       loadImage(url,imageView,null,R.mipmap.ic_launcher,R.mipmap.ic_launcher,null)
    }
}

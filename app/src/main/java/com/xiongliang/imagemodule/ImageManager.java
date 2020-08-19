package com.xiongliang.imagemodule;

import android.graphics.Bitmap;

import com.xiongliang.imagemodule.glide.IImageLoader;
import com.xiongliang.imagemodule.glide.ImageLoaderImpl;

public class ImageManager {
    static IImageLoader imageLoader = new ImageLoaderImpl();

    public static Bitmap loadImage(){
        return imageLoader.loadImage();
    }
}

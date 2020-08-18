package com.xiongliang.imagemodule.glide;

import android.graphics.Bitmap;

public class ImageLoaderImpl implements IImageLoader {
    @Override
    public void downLoadImage() {

    }

    @Override
    public Bitmap loadFromMemoryCache() {
        return null;
    }

    @Override
    public Bitmap loadFromDiskCache() {
        return null;
    }

    @Override
    public Bitmap loadImage() {
        //判断内存缓存是否有缓存
        //如果内存缓存有, 则直接返回图片
        //如果内存缓存没有, 判断磁盘缓存是否存在
        //如果磁盘缓存有, 则开启子线程, 从硬盘缓存中获取图片,直接返回图片,并且将Bitmap 保存到内存缓存
        //如果磁盘缓存没有, 则开启子线程去下载图片, 直接返回图片, 并且将下载的图片保存到磁盘缓存

        return null;
    }
}

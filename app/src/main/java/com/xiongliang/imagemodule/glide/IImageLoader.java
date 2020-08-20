package com.xiongliang.imagemodule.glide;

import android.graphics.Bitmap;

public interface IImageLoader {
    /***
     * 图片下载
     */
    void downLoadImage();

    /***
     * 从内存缓存中获取图片,缓存处理后的图片
     * @return
     */
    Bitmap loadFromMemoryCache();

    /***
     * 从磁盘缓存中获取图片, 缓存原始图片
     * @return
     */
    Bitmap loadFromDiskCache();

    /**
     * 判断内存缓存是否有缓存
     * 如果内存缓存有, 根据url+size 生成key,则直接返回图片
     * 如果内存缓存没有, 判断磁盘缓存是否存在
     * 如果磁盘缓存有, 则开启子线程, 根据url 生成key,从硬盘缓存中获取原始图片,直接返回图片, 并且根据size,生成memoey key,并且将Bitmap 保存到内存缓存
     * 如果磁盘缓存没有, 则开启子线程去下载图片, 直接返回图片, 并且将下载的图片根据 url为key 保存到磁盘缓存
     * @return
     */
    Bitmap loadImage();

    /**
     * 对图片进行压缩, 避免OOM
     * 磁盘缓存 存原始图片
     * 内存缓存 存处理后的图片
     * @return
     */
    Bitmap reSample();

    /**
     * 获取内存缓存的key, key 由url, size 决定
     * @return
     */
    String getMemoryCacheKey();

    /**
     * 获取磁盘缓存的key, key 由url 决定
     * @return
     */
    String getDiskCacheKey();
}

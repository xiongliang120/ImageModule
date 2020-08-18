package com.xiongliang.imagemodule.glide;

import android.graphics.Bitmap;

public interface IImageLoader {
    /***
     * 图片下载
     */
    void downLoadImage();

    /***
     * 从内存缓存中获取图片
     * @return
     */
    Bitmap loadFromMemoryCache();

    /***
     * 从磁盘缓存中获取图片
     * @return
     */
    Bitmap loadFromDiskCache();

    /**
     * 显示图片
     * @return
     */
    Bitmap loadImage();

    /**
     * 对图片进行压缩
     * @return
     */
    Bitmap reSample();
}

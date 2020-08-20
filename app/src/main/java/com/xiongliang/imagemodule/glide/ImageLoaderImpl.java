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
        return null;
    }

    @Override
    public Bitmap reSample() {
        //尺寸压缩通过 inSampleSize, 质量压缩 Bitmap.compress()
        return null;
    }

    @Override
    public String getMemoryCacheKey() {
        return null;
    }

    @Override
    public String getDiskCacheKey() {
        return null;
    }
}

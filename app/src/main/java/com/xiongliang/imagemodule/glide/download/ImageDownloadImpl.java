package com.xiongliang.imagemodule.glide.download;

import com.xiongliang.imagemodule.glide.download.IImageDownload;

public class ImageDownloadImpl implements IImageDownload {
    @Override
    public void downloadMultiThread(String url) {

    }

    @Override
    public void downloadSingleThread(String url) {
        ThreadPoolManager.getIOExecutor().execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}

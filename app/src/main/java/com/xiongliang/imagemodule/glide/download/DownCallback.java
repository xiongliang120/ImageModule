package com.xiongliang.imagemodule.glide.download;

import java.io.File;

public interface DownCallback {
    /**
     * 更新进度条
     * @param progress
     */
    void progress(int progress);

    /**
     * 文件下载成功
     * @param file
     */
    void loadSuccess(File file);

    /**
     * 文件下载失败
     */
    void loadFailed();
}

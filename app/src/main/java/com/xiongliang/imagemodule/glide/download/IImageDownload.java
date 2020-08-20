package com.xiongliang.imagemodule.glide.download;

import java.util.List;

/**
 * https://developer.aliyun.com/article/25382
 *
 * 多线程下载, 分片下载
 * 思路: 生产者与消费者模型(一生产一消费)   (单对多)
 * 生产者线程: 根据url 获取文件的大小, 根据大小对文件进行分片处理, 每片下载任务放在 任务集合中。
 * 消费者线程: 同步从任务集合中获取任务,执行任务
 *
 */
public interface IImageDownload {
    /**
     * 获取url 文件的长度
     * @param url
     * @return
     */
    void getFileLength(String url);

    /**
     * 多线程下载图片, 分片下载
     * @param length , url
     */
    void downloadMultiThread(int length,String url);

    /**
     * 单线程下载图片
     * @param url
     */
    void downloadSingleThread(String url);
}

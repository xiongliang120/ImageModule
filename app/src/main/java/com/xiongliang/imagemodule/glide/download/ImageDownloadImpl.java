package com.xiongliang.imagemodule.glide.download;

import com.xiongliang.imagemodule.glide.download.IImageDownload;

import java.util.ArrayList;
import java.util.List;

public class ImageDownloadImpl implements IImageDownload {
    /**
     * 任务集合
     */
    private List<Runnable> task = new ArrayList<>();

    /**
     * 生产者线程
     */
    private Thread producerThread;

    /**
     * 消费者线程
     */
    private Thread consumerThread;

    @Override
    public void addTask(Runnable runnable) {
        task.add(runnable);
    }

    @Override
    public void executeTask() throws InterruptedException {
        synchronized (task){
            while (true){
                if(task.size() != 0){
                    wait();
                }else{
                    notify();
                }
            }

        }
    }

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

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

    private int cpuCount = 3;

    /**
     * 开启子线程去添加任务,防止堵塞主线程
     * @param runnable
     */
    @Override
    public void addTask(final List<Runnable> runnable) {
        ThreadPoolManager.getIOExecutor().execute(new Runnable() {
            @Override
            public void run() {
                try{
                    synchronized(task) {
                        if(task.size() >= 0) {
                            wait();
                        }
                        task.addAll(runnable);
                        notifyAll();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

    /**
     * executeTask 必须运行在子线程中 ,不断去执行task 集合中的任务
     * @throws
     */
    @Override
    public void executeTask()  {
        try {
            synchronized (task){
                while (task.size() <= 0){
                    wait();
                }
            }
            Runnable run = task.remove(0);
            //执行任务, 根据文件的range 字段,配置url,下载到指定的目录 ,然后将下载的数据有序的合并成一个文件
            notifyAll();
        }catch (Exception e) {
          e.printStackTrace();
        }
    }

    @Override
    public void downloadMultiThread(String url) {
        for(int i = 0; i < cpuCount; i++){
            ThreadPoolManager.getIOExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    executeTask();
                }
            });
        }
    }

    @Override
    public void downloadSingleThread(String url) {
        ThreadPoolManager.getIOExecutor().execute(new Runnable() {
            @Override
            public void run() {
                executeTask();
            }
        });
    }
}

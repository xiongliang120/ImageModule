package com.xiongliang.imagemodule.glide.download;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class ImageDownloadImpl implements IImageDownload {
    /**
     * 任务集合
     */
    private List<DownRunnable> downRunnables = new ArrayList<>();

    private int cpuCount = 3;

    private DownCallback downCallback;

    /**
     * 回调子线程总数,原子操作(多并发下,一个操作不能被打断,要么全部执行完毕,要么不执行)
     */
    private AtomicInteger callbackCount = new AtomicInteger(0);


    public ImageDownloadImpl(DownCallback downCallback){
        this.downCallback = downCallback;
    }


    @Override
    public void getFileLength(String url) {
        //网络请求获取length,
    }


    @Override
    public void downloadMultiThread(int length,String url) {
        for (int i = 0; i < cpuCount; i++) {
            int start =0;
            int end = 0;
            DownRunnable downRunnable  = new DownRunnable(url, start, end, url, new DownCallback() {
                @Override
                public void progress(int progress) {
                    synchronized(this){
                        //合并进度数据, 更新UI进度,需要做同步处理
                        int totalProgress = 0;
                        downCallback.progress(totalProgress);
                    }
                }

                @Override
                public void loadSuccess(File file) {
                    //判断所有线程runnable 都执行成功,才更新提示文件下载成功提示
                    callbackCount.getAndIncrement();

                    if(callbackCount.get() == cpuCount){
                        downCallback.loadSuccess(file);
                    }
                }

                @Override
                public void loadFailed() {
                    //一旦一个线程失败,则停止所有线程任务
                    stopTasks();
                    downCallback.loadFailed();
                }
            });

            ThreadPoolManager.getIOExecutor().execute(downRunnable);
            downRunnables.add(downRunnable);
        }
    }

    public void stopTasks(){
        for (int i = 0; i < downRunnables.size(); i++) {
             downRunnables.get(i).stopRunnable();
        }
    }

    @Override
    public void downloadSingleThread(String url) {
        DownRunnable downRunnable = new DownRunnable(url,0,0,url,new DownCallback(){
            @Override
            public void progress(int progress) {

            }

            @Override
            public void loadSuccess(File file) {

            }

            @Override
            public void loadFailed() {

            }
        });

        ThreadPoolManager.getIOExecutor().execute(downRunnable);
    }
}

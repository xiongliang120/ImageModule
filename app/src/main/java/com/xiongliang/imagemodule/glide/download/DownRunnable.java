package com.xiongliang.imagemodule.glide.download;

public class DownRunnable implements Runnable{
    private String url;
    private int start;
    private int end;
    private String filePath;
    private DownCallback downCallback;

    public DownRunnable(String url,int start,int end,String filePath,DownCallback downCallback){
        this.url = url;
        this.start = start;
        this.end = end;
        this.filePath = filePath;
        this.downCallback = downCallback;
    }


    @Override
    public void run() {
         //OkHttp 去根据url,start,end 去下载文件, 然后通过RandomAccessFile.seekTo() 写入指定位置
    }

    /**
     * 停止任务
     */
    public void stopRunnable(){

    }


}

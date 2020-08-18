package com.xiongliang.imagemodule.glide.download;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池管理
 */
public class ThreadPoolManager {
    private static ExecutorService sIOThreadPoolExecutor;

    private static final DefaultThreadFactory sThreadFactory = new DefaultThreadFactory();

    static {
        /**
         * 创建一个可缓存的线程池,如果线程池长度超过处理需要,可灵活回收空闲线程,若无可回收, 则新建线程
         */
        sIOThreadPoolExecutor = Executors.newCachedThreadPool(sThreadFactory);
    }

    /**
     * 获取IO线程池
     * @return
     */
    public static ExecutorService getIOExecutor() {
        return sIOThreadPoolExecutor;
    }


    /**
     * The default thread factory.
     */
    private static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "TaskDispatcherPool-" +
                    poolNumber.getAndIncrement() +
                    "-Thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
}

package lanou.com.fakebilibili.utils.animatorutils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * .       _ooOoo_
 * .      o8888888o
 * .      88" . "88
 * .      (| -_- |)
 * .      O\  =  /O
 * .   ____/`---'\____
 * . .'  \\|     |//  `.
 * ./  \\|||  :  |||//  \
 *
 * Created by Zach on 2017/3/22.
 */
public class MyThreadPool {
    private static MyThreadPool ourInstance ;
    private final ThreadPoolExecutor threadPoolExecutor;

    public static MyThreadPool getInstance() {
        if (ourInstance==null){
            synchronized (MyThreadPool.class){
                if (ourInstance==null){
                    ourInstance=new MyThreadPool();
                }
            }
        }
        return ourInstance;
    }

    private MyThreadPool() {
        int lines=Runtime.getRuntime().availableProcessors();
        threadPoolExecutor = new ThreadPoolExecutor(lines+1,
                2*lines+1,30, TimeUnit.SECONDS
                ,new ArrayBlockingQueue<Runnable>(10));
    }

    public ThreadPoolExecutor getThreadPoolExecutor(){
        return threadPoolExecutor;
    }
}

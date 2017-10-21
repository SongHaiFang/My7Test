package demo.song.com.my7test.utils;

import android.util.Log;

public class CauchExceptionHandler implements Thread.UncaughtExceptionHandler {

    private static CauchExceptionHandler cauchExceptionHandler = null;

    private CauchExceptionHandler() {

    }

    public static CauchExceptionHandler getInstance() {

        if (cauchExceptionHandler == null) {

            synchronized (CauchExceptionHandler.class) {

                if (cauchExceptionHandler == null) {
                    cauchExceptionHandler = new CauchExceptionHandler();
                }
            }
        }

        return cauchExceptionHandler;
    }

    public void setDefaultUnCachExceptionHandler() { //在Application开始时调用

        Thread.setDefaultUncaughtExceptionHandler(this); //设置应用默认的全局捕获异常器
    }

    @Override //应用没有捕抓的异常会到这里来,如果我们设置了应用的默认全局捕抓异常为CauchExceptionHandler的话
    public void uncaughtException(Thread thread, Throwable throwable) {

        Log.d("CauchExceptionHandler", "程序出现异常"+throwable); //异常信息
//        Logger.d("CauchExceptionHandler", "程序出现异常");
    }
}
package demo.song.com.my7test;

import android.app.Application;

import demo.song.com.my7test.utils.CauchExceptionHandler;

/**
 * data:2017/10/21 0021.
 * Created by ：宋海防  song on
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CauchExceptionHandler.getInstance().setDefaultUnCachExceptionHandler();
    }

}


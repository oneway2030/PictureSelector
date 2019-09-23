package com.luck.pictureselector;

import android.app.Application;

import com.luck.picture.lib.loader.AlbumImageLoaderManager;
import com.squareup.leakcanary.LeakCanary;


/**
 * author：luck
 * project：PictureSelector
 * package：com.luck.pictureselector
 * email：893855882@qq.com
 * data：2017/4/29
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        LeakCanary.install(this);
        //自定义图片实现
        AlbumImageLoaderManager.setLoader(new CustomImageLoaderMpl());
    }
}

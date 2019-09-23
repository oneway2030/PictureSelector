package com.luck.picture.lib.loader;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * 静态图片Bitmap加载监听
 */
public interface LoadBitmapListener {
    /**
     * 加载成功回调
     */
    void onResourceReady(Bitmap resource);

    /**
     * 加载失败回调
     */
    default void onLoadFailed(Drawable errorDrawable) {
    }
}

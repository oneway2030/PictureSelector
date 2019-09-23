package com.luck.picture.lib.loader;

/**
 * Git加载监听
 */
public interface LoadGifListener {
    /**
     * 加载失败回调
     */
    void onLoadFailed();

    /**
     * 加载成功回调
     */
    void onResourceReady();
}

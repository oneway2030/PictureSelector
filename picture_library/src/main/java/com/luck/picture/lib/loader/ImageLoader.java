package com.luck.picture.lib.loader;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

/**
 * 加载图片 接口
 * 自定义图片实现
 * 1.如果需要自定义加载器, 需要实现接口 {@link ImageLoader},
 * 2.并在App初始化中调用setLoader {@link AlbumImageLoaderManager#setLoader(ImageLoader)}
 * 3.具体实现,可以参照此框架内默认实现示列 {@link DefImageLoaderMpl}
 */
public interface ImageLoader {

    /**
     * 静态加载图片 到指定view
     *
     * @param context          上下文
     * @param path             图片地址
     * @param tragetIv         加载到目标view
     * @param placeholderResId 占位图片资源id
     */
    void loadImage(Context context, Object path, ImageView tragetIv, int placeholderResId);


    /**
     * 将目标以静态图片方式加载到目标view
     *
     * @param context          上下文
     * @param path             图片地址
     * @param tragetIv         加载到目标view
     * @param placeholderResId 占位图片资源id
     * @param overrideWidth    重置的固定宽
     * @param overrideHeight   重置的固定高
     * @param sizeMultiplier   所列图比例
     */
    void loadImageAsBitmap(Context context, Object path, ImageView tragetIv, int placeholderResId, int overrideWidth, int overrideHeight, float sizeMultiplier);

    /**
     * 将目标以静态 圆形图片 方式加载到目标view (加载圆形图片)
     *
     * @param context          上下文
     * @param path             图片地址
     * @param tragetIv         加载到目标view
     * @param placeholderResId 占位图片资源id
     * @param overrideWidth    重置的固定宽
     * @param overrideHeight   重置的固定高
     * @param sizeMultiplier   所列图比例
     */
    void loadRoundImageAsBitmap(Context context, Object path, ImageView tragetIv, int placeholderResId, int overrideWidth, int overrideHeight, float sizeMultiplier);


    /**
     * 加载GIf 图片 不从任何缓存读取
     *
     * @param context        上下文
     * @param path           图片地址
     * @param tragetIv       加载到目标view
     * @param overrideWidth  重置的固定宽
     * @param overrideHeight 重置的固定高
     */
    void loadNoCacheAsGit(Context context, Object path, ImageView tragetIv, int overrideWidth, int overrideHeight);

    /**
     * 将目标地址转换成   bitmap
     *
     * @param context  上下文
     * @param path     图片地址
     * @param listener 转换监听
     */
    void getBitmap(Context context, Object path, LoadBitmapListener listener);

    /**
     * 加载GIf 图片 不从任何缓存读取 并获取请求监听
     *
     * @param context        上下文
     * @param path           图片地址
     * @param tragetIv       加载到目标view
     * @param overrideWidth  重置的固定宽
     * @param overrideHeight 重置的固定高
     * @param listener       请求监听
     */
    void getNoCacheAsGitAndListener(Activity context, Object path, ImageView tragetIv, int overrideWidth, int overrideHeight, LoadGifListener listener);


}

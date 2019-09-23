package com.yalantis.ucrop.load;

import android.content.Context;
import android.widget.ImageView;

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
}

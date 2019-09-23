package com.luck.picture.lib.loader;

/**
 * 相册的 图片加载管理器
 * <p>
 * 自定义图片实现
 * 1.如果需要自定义加载器, 需要实现接口 {@link ImageLoader},
 * 2.并在App初始化中调用setLoader {@link AlbumImageLoaderManager#setLoader(ImageLoader)}
 * 3.具体实现,可以参照此框架内默认实现示列 {@link DefImageLoaderMpl}
 */
public class AlbumImageLoaderManager {
    private static ImageLoader innerLoader;
    private static ImageLoader externalLoader;

    public static void setLoader(ImageLoader loader) {
        if (externalLoader == null && loader != null) {
            externalLoader = loader;
        }
    }

    public static ImageLoader getLoader() {
        if (innerLoader == null) {
            synchronized (AlbumImageLoaderManager.class) {
                if (innerLoader == null) {
                    if (externalLoader != null) {
                        innerLoader = externalLoader;
                    } else {
                        innerLoader = new DefImageLoaderMpl();
                    }
                }
            }
        }
        return innerLoader;
    }
}

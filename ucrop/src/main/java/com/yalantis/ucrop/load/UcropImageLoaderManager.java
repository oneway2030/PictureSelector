package com.yalantis.ucrop.load;

public class UcropImageLoaderManager {
    private static ImageLoader mImageLoader;


    public static void setInstance(ImageLoader imageLoader) {
        mImageLoader = imageLoader;
    }

    public static ImageLoader getLoader() {
        return mImageLoader;
    }
}

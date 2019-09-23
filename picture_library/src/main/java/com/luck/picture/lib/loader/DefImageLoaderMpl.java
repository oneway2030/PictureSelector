package com.luck.picture.lib.loader;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

/**
 * 默认的  图片加载实现
 * 自定义图片实现
 * 1.如果需要自定义加载器, 需要实现接口 {@link ILoader},
 * 2.并在App初始化中调用setLoader {@link AlbumImageLoaderManager#setLoader(ILoader)}
 * 3.具体实现,可以参照此框架内默认实现示列 {@link DefImageLoaderMpl}
 */
public class DefImageLoaderMpl implements ILoader {
    @Override
    public void loadNoCacheAsGit(Context context, Object path, ImageView tragetIv, int overrideWidth, int overrideHeight) {
        RequestOptions gifOptions = new RequestOptions()
                .override(overrideWidth, overrideHeight)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(context)
                .asGif()
                .load(path)
                .apply(gifOptions)
                .into(tragetIv);
    }

    @Override
    public void getNoCacheAsGitAndListener(Activity context, Object path, ImageView tragetIv, int overrideWidth, int overrideHeight, LoadGifListener listener) {
        RequestOptions gifOptions = new RequestOptions()
                .override(overrideWidth, overrideHeight)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(context)
                .asGif()
                .apply(gifOptions)
                .load(path)
                .listener(new RequestListener<GifDrawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model
                            , Target<GifDrawable> target, boolean isFirstResource) {
                        listener.onLoadFailed();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GifDrawable resource, Object model
                            , Target<GifDrawable> target, DataSource dataSource,
                                                   boolean isFirstResource) {
                        listener.onResourceReady();
                        return false;
                    }
                })
                .into(tragetIv);
    }

    @Override
    public void getBitmap(Context context, Object path, LoadBitmapListener listener) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context)
                .asBitmap()
                .load(path)
                .apply(options)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        if (listener != null)
                            listener.onResourceReady(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        if (listener != null)
                            listener.onLoadFailed(errorDrawable);
                    }
                });
    }

    @Override
    public void loadImageAsBitmap(Context context, Object path, ImageView tragetIv, int placeholderResId, int overrideWidth, int overrideHeight, float sizeMultiplier) {
        RequestOptions options = new RequestOptions();
        if (overrideWidth <= 0 && overrideHeight <= 0) {
            options.sizeMultiplier(sizeMultiplier);
        } else {
            options.override(overrideWidth, overrideHeight);
        }
        options.diskCacheStrategy(DiskCacheStrategy.ALL);
        options.centerCrop();
        options.placeholder(placeholderResId);
        Glide.with(context)
                .asBitmap()
                .load(path)
                .apply(options)
                .into(tragetIv);
    }

    @Override
    public void loadRoundImageAsBitmap(Context context, Object path, ImageView tragetIv, int placeholderResId, int overrideWidth, int overrideHeight, float sizeMultiplier) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeholderResId)
                .centerCrop()
                .sizeMultiplier(sizeMultiplier)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(overrideWidth, overrideHeight);
        Glide.with(context)
                .asBitmap()
                .load(path)
                .apply(options)
                .into(new BitmapImageViewTarget(tragetIv) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.
                                        create(context.getResources(), resource);
                        circularBitmapDrawable.setCornerRadius(8);
                        tragetIv.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }
}

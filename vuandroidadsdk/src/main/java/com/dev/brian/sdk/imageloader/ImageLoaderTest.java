package com.dev.brian.sdk.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at:2017/11/16 0016
 * Description:
 */

public class ImageLoaderTest {
    private Context context;
    private ImageView imageView;

    private void testApi() {

        /**
         * 为我们的ImageLoader去配置参数
         */
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration
                .Builder(context)
                .build();
        /**
         * 我们先来获取到ImageLoader的一个实例
         */
        ImageLoader imageLoader = ImageLoader.getInstance();

        /**
         * 为我们显示图片的时候去进行一个配置
         */
        DisplayImageOptions options = new DisplayImageOptions
                .Builder()
                .build();

        /**
         * 使用displayImage去加载图片
         */
        imageLoader.displayImage("url", imageView, options, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                super.onLoadingCancelled(imageUri, view);
            }

            @Override
            public void onLoadingStarted(String imageUri, View view) {
                super.onLoadingStarted(imageUri, view);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                super.onLoadingFailed(imageUri, view, failReason);
            }
        });

        ImageLoaderManager.getInstance(context).displayImage(imageView, "url");
    }
}

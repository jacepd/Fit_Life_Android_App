package com.example.Fit_Life;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {

    private static VolleySingleton mInstance;
    private ImageLoader mImageLoader;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    public VolleySingleton(Context context) {
        mContext = context.getApplicationContext();
        mRequestQueue = getRequestQueue();
        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> cache = new LruCache<>(40);

            @Override
            public Bitmap getBitmap(String url) {
                final Bitmap bitmap = cache.get(url);
                return bitmap;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) { cache.put(url, bitmap); }
        });
    }

    // Factory method to get the Singleton instance.
    // Synchronized makes it thread-safe.
    public static synchronized VolleySingleton getInstance(Context context){
        if (mInstance == null){
            mInstance = new VolleySingleton(context);
        }

        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null){

            // Guard against someone sending in the wrong context.
            mRequestQueue= Volley.newRequestQueue(mContext.getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request, String tag){
        request.setTag(tag);
        getRequestQueue().add(request);
    }

    public void cancelAllRequests(String tag){
        getRequestQueue().cancelAll(tag);
    }

    public ImageLoader getImageLoader(){ return mImageLoader; }
}
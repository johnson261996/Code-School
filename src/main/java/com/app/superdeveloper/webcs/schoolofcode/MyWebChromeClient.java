package com.app.superdeveloper.webcs.schoolofcode;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by johnson on 04-06-2017.
 */
public class MyWebChromeClient extends WebChromeClient {
    private ProgressListener mListener;

    public MyWebChromeClient(ProgressListener listener) {
        mListener = listener;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        mListener.onUpdateProgress(newProgress);
        super.onProgressChanged(view, newProgress);
    }

    public interface ProgressListener {
        public void onUpdateProgress(int progressValue);
    }
}

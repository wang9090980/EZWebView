package com.hkm.ezwebviewsample;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import com.hkm.ezwebview.Util.Fx9C;
import com.hkm.ezwebview.Util.In32;
import com.hkm.ezwebview.webviewclients.HClient;
import com.hkm.ezwebview.webviewclients.URLClient;
import com.hkm.ezwebview.webviewleakfix.NonLeakingWebView;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by hesk on 1/9/15.
 */
public class VideoFra extends Fragment implements URLClient.cb, HClient.Callback {

    private NonLeakingWebView block;
    private CircleProgressBar betterCircleBar;
    private RelativeLayout framer;

    private List<String> getInternal() {
        final List<String> h = new ArrayList<>();

        return h;
    }

    private List<String> getAllow() {
        final List<String> h = new ArrayList<>();
        h.add("techcrunch.com");
        h.add("google.com");
        h.add("google.com.hk");
        return h;
    }

    protected int get_layout_id() {
        return com.hkm.ezwebview.R.layout.videof;
    }


    @SuppressLint("ResourceAsColor")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    protected void initBinding(View v) {
        betterCircleBar = (CircleProgressBar) v.findViewById(com.hkm.ezwebview.R.id.progressloadingbarpx);
        block = (NonLeakingWebView) v.findViewById(com.hkm.ezwebview.R.id.videoplayer);
        framer = (RelativeLayout) v.findViewById(com.hkm.ezwebview.R.id.framevideoplayer);
    }

    private void killWebView(NonLeakingWebView mWebView) {
        //http://stackoverflow.com/questions/3815090/webview-and-html5-video
        if (mWebView.getVisibility() == View.GONE) {
            mWebView.loadUrl("about:blank");
            mWebView.destroy();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(get_layout_id(), container, false);
    }


    protected void completeloading() {
        ViewCompat.animate(betterCircleBar).alpha(0f).withEndAction(new Runnable() {
            @Override
            public void run() {
                betterCircleBar.setVisibility(View.GONE);
            }
        });
    }

    public void kill() {
        killWebView(block);
    }

    private static String fromFileRaw(Context ctx, final @RawRes int resource_raw_file_name) {
        StringBuilder sb = new StringBuilder();
        Scanner s = new Scanner(ctx.getResources().openRawResource(resource_raw_file_name));
        while (s.hasNextLine()) {
            sb.append(s.nextLine() + "\n");
        }
        return sb.toString();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initBinding(view);
        setup_video();
    }

    /**
     * This is the example code
     */
    private void setup_video() {
        final String contentc = fromFileRaw(getActivity(), R.raw.sample_html);
        try {
            Fx9C.setup_web_video(
                    this,
                    framer,
                    block,
                    betterCircleBar,
                    contentc,
                    2000,
                    this, null);

        } catch (Exception e) {
        }

    }

    @Override
    public void triggerNative(Uri trigger_url) {

    }

    @Override
    public boolean interceptUrl(String url, WebView wb) {
        return In32.interceptURL_cart(url, getAllow(), getInternal(), this);
    }
}

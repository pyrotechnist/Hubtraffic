package com.example.longyuan.hubtraffic.videodetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.GridView;
import android.widget.TextView;

import com.example.longyuan.hubtraffic.R;
import com.example.longyuan.hubtraffic.main.MainFragment;
import com.example.longyuan.hubtraffic.pojo.video.VideosItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LONGYUAN on 2018/2/10.
 */

public class VideoDetailFragment extends Fragment implements VideoDetailContract.View{

    private VideoDetailContract.Presenter mVideoDetailPresenter;

    @BindView(R.id.textview_test)
    TextView mTextView;

    @BindView(R.id.video_detail_webview)
    WebView mWebView;

    public static VideoDetailFragment getInstance(){
        return new VideoDetailFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_video_detail, container, false);

        ButterKnife.bind(this, view);

        WebSettings webSettings = mWebView.getSettings();

        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        return view;
    }

    @Override
    public void setPresenter(VideoDetailContract.Presenter presenter) {

        mVideoDetailPresenter = presenter;
    }


    @Override
    public void updateData(VideosItem videosItem) {

        mTextView.setText(videosItem.getTitle());



       // mWebView.loadUrl("https://voice.hupu.com/soccer");

        mWebView.loadData("<iframe src=\"https://www.pornhub.com/embed/"+videosItem.getVideoId()+"\" frameborder=\"0\" width=\"360\" height=\"468\" scrolling=\"no\"></iframe>","text/html", "utf-8");

    }

    @Override
    public void onResume() {
        super.onResume();
        mVideoDetailPresenter.start();
    }
}

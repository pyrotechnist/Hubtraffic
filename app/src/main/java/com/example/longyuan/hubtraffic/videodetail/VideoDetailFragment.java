package com.example.longyuan.hubtraffic.videodetail;

import android.os.Bundle;
import android.support.annotation.Nullable;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.longyuan.hubtraffic.videodetail.VideoDetailActivity.EXTRA_VIDEO_ID;

/**
 * Created by LONGYUAN on 2018/2/10.
 */

public class VideoDetailFragment extends Fragment implements VideoDetailContract.View{

    private VideoDetailContract.Presenter mVideoDetailPresenter;

    @BindView(R.id.textview_test)
    TextView mTextView;

    @BindView(R.id.video_detail_webview)
    WebView mWebView;

    @BindView(R.id.video_detail_tabs)
    TabLayout mTableLayout;


    @BindView(R.id.video_detail_viewpager)
    ViewPager mViewPager;

    private String mVideo_Id;


    public static VideoDetailFragment getInstance(String videoId){
        VideoDetailFragment videoDetailFragment = new VideoDetailFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_VIDEO_ID, videoId);
        videoDetailFragment.setArguments(args);
        return videoDetailFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (getArguments() != null) {
            mVideo_Id = getArguments().getString(EXTRA_VIDEO_ID);
        }

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



        if (mViewPager != null) {
            setupViewPager(mViewPager);


        }
        mTableLayout.setupWithViewPager(mViewPager);

        return view;
    }


    private void setupViewPager(ViewPager viewPager) {

        Adapter adapter = new Adapter(getChildFragmentManager());

        VideoDetailTabInfFragment videoDetailTabInfFragment = VideoDetailTabInfFragment.newInstance(mVideo_Id);


        //promotionsFragment1.setPromotionsRepository(mPromotionsRepository);
        //promotionsFragment1.setCategory(Category.Better);
        adapter.addFragment(videoDetailTabInfFragment, "User Details");

        VideoDetailTabVideosFragment videoDetailTabVideosFragment = VideoDetailTabVideosFragment.newInstance(mVideo_Id);

        //promotionsFragment1.setPromotionsRepository(mPromotionsRepository);
        //promotionsFragment1.setCategory(Category.Better);
      /*  promotionsFragment2.setPromotionsRepository(mPromotionsRepository);
        promotionsFragment2.setCategory(Category.Good);*/
        adapter.addFragment(videoDetailTabVideosFragment, "User Actions");

        viewPager.setAdapter(adapter);
    }

    @Override
    public void setPresenter(VideoDetailContract.Presenter presenter) {

        mVideoDetailPresenter = presenter;
    }


    @Override
    public void updateData(VideosItem videosItem) {

        mTextView.setText(videosItem.getTitle());



       // mWebView.loadUrl("https://voice.hupu.com/soccer");

        mWebView.loadData("<iframe src=\"https://www.pornhub.com/embed/"+videosItem.getVideoId()+"\" frameborder=\"0\" width=\"350\" height=\"300\" scrolling=\"no\"></iframe>","text/html", "utf-8");

    }

    @Override
    public void onResume() {
        super.onResume();
        mVideoDetailPresenter.start();
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}

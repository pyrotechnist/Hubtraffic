package com.example.longyuan.hubtraffic.videodetail;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.longyuan.hubtraffic.App;
import com.example.longyuan.hubtraffic.R;

import javax.inject.Inject;

public class VideoDetailActivity extends AppCompatActivity {

    public static final String EXTRA_VIDEO_ID = "VIDEO_ID";

    @Inject
    protected VideoDetailContract.Presenter mPresenter;

    private String mVideoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mVideoId = getIntent().getStringExtra(EXTRA_VIDEO_ID);


        VideoDetailFragment universityDetailsFragment = (VideoDetailFragment) getSupportFragmentManager().findFragmentById(R.id.frag_video_detail_content);

        if(universityDetailsFragment == null)
        {
            universityDetailsFragment =  VideoDetailFragment.getInstance(mVideoId);

            getSupportFragmentManager().beginTransaction().add(R.id.frag_video_detail_content,universityDetailsFragment).commit();

        }
        String videoId = getIntent().getStringExtra(EXTRA_VIDEO_ID);

        DaggerVideoDetailComponent.builder()
                .appComponent(((App)getApplication()).getAppComponent())
                .videoDetailModule(new VideoDetailModule(universityDetailsFragment,videoId))
                .build()
                .inject(this);

    }

}

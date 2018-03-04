package com.example.longyuan.hubtraffic.welcome;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;

import com.example.longyuan.hubtraffic.App;
import com.example.longyuan.hubtraffic.R;
import com.example.longyuan.hubtraffic.datastore.DataStore;
import com.example.longyuan.hubtraffic.datastore.VideoRepository;
import com.example.longyuan.hubtraffic.main.MainActivity;
import com.example.longyuan.hubtraffic.pojo.category.Category;
import com.example.longyuan.hubtraffic.pojo.star.StarsItem;
import com.example.longyuan.hubtraffic.pojo.video.VideosItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity {

    @Inject
    protected VideoRepository mVideoRepository;


    @BindView(R.id.progressBar1)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        ButterKnife.bind(this);

        App.getAppComponent().inject(this);

        mProgressBar.setVisibility(View.VISIBLE);

        loadVideos();






    }

    private void loadVideos(){

        mVideoRepository.loadVideos(new DataStore.LoadVideosCallback() {
            @Override
            public void onVideosLoaded(List<VideosItem> videosItems) {
                loadCategories();
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void loadStar(){

        mVideoRepository.loadStars(new DataStore.LoadStarsCallback() {
            @Override
            public void onStarsLoaded(List<StarsItem> starsItems) {
                loadCategories();
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void loadCategories(){

        mVideoRepository.loadCategories(new DataStore.LoadCategoriesCallback() {
            @Override
            public void onCategoriesLoaded(List<Category> categories) {

                Intent intent = new Intent(getBaseContext(),MainActivity.class);

                startActivity(intent);

                mProgressBar.setVisibility(View.GONE);

                finish();
            }

            @Override
            public void onError(String error) {

            }
        });
    }
}

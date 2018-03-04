package com.example.longyuan.hubtraffic.stardetail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.longyuan.hubtraffic.App;
import com.example.longyuan.hubtraffic.R;
import com.example.longyuan.hubtraffic.datastore.DataStore;
import com.example.longyuan.hubtraffic.datastore.VideoRepository;
import com.example.longyuan.hubtraffic.main.MainActivity;
import com.example.longyuan.hubtraffic.pojo.star.StarsItem;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.longyuan.hubtraffic.star.StarsActivity.EXTRA_STAR;

public class StarDetailActivity extends AppCompatActivity {

    @Inject
    protected VideoRepository mVideoRepository;


    @BindView(R.id.textView_name_title)
    TextView mTextView_name_title;

    @BindView(R.id.textView_name)
    TextView mTextView_name;

    @BindView(R.id.textView_count_title)
    TextView mTextView_count_title;

    @BindView(R.id.textView_count)
    TextView mTextView_count;

    @BindView(R.id.button_url)
    Button mButton_Url;

    @BindView(R.id.button_videos)
    Button mButton_Video;

    @BindView(R.id.stardetail_image)
    ImageView mImageView;


    private String mStarName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_detail);

        ButterKnife.bind(this);

        App.getAppComponent().inject(this);

        mStarName = getIntent().getStringExtra(EXTRA_STAR);

        loadStarDetail(mStarName);

    }

    private void loadStarDetail(String starName) {

        mVideoRepository.loadStarDetail(new DataStore.LoadStarItemCallback() {
            @Override
            public void onStarItemLoaded(StarsItem starsItem) {

                updateView(starsItem);
            }

            @Override
            public void onError(String error) {

            }
        },mStarName);
    }

    private void updateView(StarsItem starsItem) {

        mTextView_count.setText(starsItem.getStar().getVideosCount());

        mTextView_name.setText(starsItem.getStar().getStarName());

        mButton_Url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(starsItem.getStar().getStarUrl()));
                startActivity(openBrowser);
            }
        });

        mButton_Video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getBaseContext(), MainActivity.class);

                intent.putExtra(EXTRA_STAR, mStarName);

                startActivity(intent);
            }
        });

        Glide.with(this).load(starsItem.getStar().getStarThumb()).into(mImageView);

    }

}

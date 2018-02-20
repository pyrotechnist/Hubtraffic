package com.example.longyuan.hubtraffic.star;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.longyuan.hubtraffic.App;
import com.example.longyuan.hubtraffic.R;

import javax.inject.Inject;

public class StarsActivity extends AppCompatActivity {

    public static final String EXTRA_STAR= "STAR";

    @Inject
    protected StarsContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stars);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        StarsFragment starsFragment = (StarsFragment) getSupportFragmentManager().findFragmentById(R.id.frag_stars_content);

        if(starsFragment == null)
        {
            starsFragment =  StarsFragment.getInstance();

            getSupportFragmentManager().beginTransaction().add(R.id.frag_stars_content,starsFragment).commit();

        }

        DaggerStarsComponent.builder()
                .appComponent(((App)getApplication()).getAppComponent())
                .starsModule(new StarsModule(starsFragment))
                .build()
                .inject(this);

    }

}

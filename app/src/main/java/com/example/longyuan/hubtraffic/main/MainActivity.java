package com.example.longyuan.hubtraffic.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.longyuan.hubtraffic.App;
import com.example.longyuan.hubtraffic.R;
import com.example.longyuan.hubtraffic.category.CategoryActivity;

import javax.inject.Inject;

import static com.example.longyuan.hubtraffic.star.StarsActivity.EXTRA_STAR;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Inject
    protected MainContract.Presenter mMainPresenter;

    public static final String EXTRA_TAG = "TAG";

    public static final String EXTRA_CATEGORY = "CATEGORY";

    public static final String EXTRA_STAR = "STAR";

    private String mTag;

    private String mCategory;

    private String mStarName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpToolbar();

        mTag = getIntent().getStringExtra(EXTRA_TAG);

        mCategory = getIntent().getStringExtra(EXTRA_CATEGORY);

        mStarName = getIntent().getStringExtra(EXTRA_STAR);


        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.frag_main);

        if(mainFragment == null)
        {
            mainFragment = MainFragment.getInstance();
            Bundle args = new Bundle();
            if(mTag != null){


                args.putString(EXTRA_TAG, mTag);


            }

            if(mCategory != null){

                args.putString(EXTRA_CATEGORY, mCategory);
            }

            if(mStarName != null){

                args.putString(EXTRA_STAR, mStarName);

            }

            if(!args.isEmpty())
            mainFragment.setArguments(args);

            getSupportFragmentManager().beginTransaction().add(R.id.frag_main,mainFragment).commit();
        }

        DaggerMainComponent.builder()
                .appComponent(((App)getApplication()).getAppComponent())
                .mainModule(new MainModule(mainFragment))
                .build()
                .inject(this);

    }

    private void setUpToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_category) {

            Intent intent = new Intent(this, CategoryActivity.class);


            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

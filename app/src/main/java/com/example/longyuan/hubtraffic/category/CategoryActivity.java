package com.example.longyuan.hubtraffic.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.longyuan.hubtraffic.App;
import com.example.longyuan.hubtraffic.R;
import com.example.longyuan.hubtraffic.datastore.DataStore;
import com.example.longyuan.hubtraffic.datastore.VideoRepository;
import com.example.longyuan.hubtraffic.main.MainActivity;
import com.example.longyuan.hubtraffic.pojo.category.Category;
import com.example.longyuan.hubtraffic.util.CategoryListAdapter;
import com.example.longyuan.hubtraffic.util.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.longyuan.hubtraffic.main.MainActivity.EXTRA_CATEGORY;

public class CategoryActivity extends AppCompatActivity {


    @Inject
    protected VideoRepository mVideoRepository;

    private CategoryListAdapter mCategoryListAdapter;

    private List<Category> mCategoryList;


    @BindView(R.id.category_list)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
/*        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        ButterKnife.bind(this);

        App.getAppComponent().inject(this);

        mCategoryListAdapter = new CategoryListAdapter(new ArrayList<Category>(), this);

        mRecyclerView.setAdapter(mCategoryListAdapter);

        // mLatestPostList.setLayoutManager(new LinearLayoutManager(mLatestPostList.getContext()));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mRecyclerView.getContext(),3);

        gridLayoutManager.setAutoMeasureEnabled(false);

        mRecyclerView.setLayoutManager(gridLayoutManager);

        mCategoryListAdapter.setOnCategoryItemClickListener(new OnItemClickListener.OnCategoryItemClickListener() {
            @Override
            public void onItemClick(Category item) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);

                intent.putExtra(EXTRA_CATEGORY, item.getCategory());

                startActivity(intent);
            }
        });


        mVideoRepository.loadCategories(new DataStore.LoadCategoriesCallback() {
            @Override
            public void onCategoriesLoaded(List<Category> categories) {
                mCategoryListAdapter.updateData(categories);
            }

            @Override
            public void onError(String error) {

            }
        });


    }

}

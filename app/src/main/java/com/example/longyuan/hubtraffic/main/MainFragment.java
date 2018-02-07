package com.example.longyuan.hubtraffic.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.longyuan.hubtraffic.R;
import com.example.longyuan.hubtraffic.pojo.video.VideosItem;
import com.example.longyuan.hubtraffic.util.VideoListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LONGYUAN on 2018/2/7.
 */

public class MainFragment extends Fragment implements MainContract.View{


    private MainContract.Presenter mPresenter;

    private VideoListAdapter mVideoListAdapter;

    @BindView(R.id.videos_list)
    RecyclerView mLatestPostList;

    @BindView(R.id.videos_swipe)
    SwipeRefreshLayout mSwipeRefreshLayout;

    public static MainFragment getInstance(){
        return new MainFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.frag_main, container, false);
        ButterKnife.bind(this, view);
        // TODO Use fields...

        mVideoListAdapter = new VideoListAdapter(new ArrayList<VideosItem>(),getContext());

        mLatestPostList.setAdapter(mVideoListAdapter);

        mLatestPostList.setLayoutManager(new LinearLayoutManager(mLatestPostList.getContext()));

        return view;

    }

    @Override
    public void setPresnter(MainContract.Presenter presnter) {

        mPresenter = presnter;
    }


    @Override
    public void updateData(List<VideosItem> videosItemList) {
      // mTextView.setText(videosItemList.get(0).getTitle());

        mVideoListAdapter.updateData(videosItemList);
    }
}

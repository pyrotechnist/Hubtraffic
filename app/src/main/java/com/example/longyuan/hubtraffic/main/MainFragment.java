package com.example.longyuan.hubtraffic.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.longyuan.hubtraffic.R;
import com.example.longyuan.hubtraffic.pojo.video.VideosItem;
import com.example.longyuan.hubtraffic.util.OnItemClickListener;
import com.example.longyuan.hubtraffic.util.VideoListAdapter;
import com.example.longyuan.hubtraffic.util.VideoListGridAdapter;
import com.example.longyuan.hubtraffic.videodetail.VideoDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.longyuan.hubtraffic.videodetail.VideoDetailActivity.EXTRA_VIDEO_ID;

/**
 * Created by LONGYUAN on 2018/2/7.
 */

public class MainFragment extends Fragment implements MainContract.View{


    private MainContract.Presenter mPresenter;

    //private VideoListAdapter mVideoListAdapter;

    private VideoListGridAdapter mVideoListGridAdapter;

  /*  @BindView(R.id.videos_list)
    RecyclerView mLatestPostList;*/

     @BindView(R.id.videos_list)
     GridView mLatestPostList;

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


        //View view = inflater.inflate(R.layout.frag_main, container, false);

        View view = inflater.inflate(R.layout.frag_main_grid, container, false);

        ButterKnife.bind(this, view);
        // TODO Use fields...

        mVideoListGridAdapter =new VideoListGridAdapter(getContext(),new ArrayList<VideosItem>());

        mVideoListGridAdapter.setOnVideoItemClickListener(new OnItemClickListener.OnVideoItemClickListener() {
            @Override
            public void onItemClick(VideosItem item) {
                Intent intent = new Intent(getContext(),VideoDetailActivity.class);

                intent.putExtra(EXTRA_VIDEO_ID,item.getVideoId());

                getContext().startActivity(intent);
            }
        });

        mLatestPostList.setAdapter(mVideoListGridAdapter);

        //mLatestPostList.setNumColumns(1);

      /*  mVideoListAdapter = new VideoListAdapter(new ArrayList<VideosItem>(),getContext());

        mLatestPostList.setAdapter(mVideoListAdapter);*/

        //mLatestPostList.setLayoutManager(new LinearLayoutManager(mLatestPostList.getContext()));



        return view;

    }

    @Override
    public void setPresnter(MainContract.Presenter presnter) {

        mPresenter = presnter;
    }


    @Override
    public void updateData(List<VideosItem> videosItemList) {
      // mTextView.setText(videosItemList.get(0).getTitle());

        mVideoListGridAdapter.updateData(videosItemList);
    }

    @Override
    public void errorToast(String error) {
        Toast.makeText(getContext(),error,Toast.LENGTH_LONG).show();
    }
}

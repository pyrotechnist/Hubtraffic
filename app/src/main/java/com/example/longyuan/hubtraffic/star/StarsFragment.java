package com.example.longyuan.hubtraffic.star;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.longyuan.hubtraffic.R;
import com.example.longyuan.hubtraffic.main.MainActivity;
import com.example.longyuan.hubtraffic.pojo.star.StarsItem;
import com.example.longyuan.hubtraffic.stardetail.StarDetailActivity;
import com.example.longyuan.hubtraffic.util.OnItemClickListener;
import com.example.longyuan.hubtraffic.util.StarListAdapter;
import com.example.longyuan.hubtraffic.videodetail.VideoDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.longyuan.hubtraffic.star.StarsActivity.EXTRA_STAR;
import static com.example.longyuan.hubtraffic.videodetail.VideoDetailActivity.EXTRA_VIDEO_ID;

/**
 * Created by LONGYUAN on 2018/2/19.
 */

public class StarsFragment extends Fragment implements StarsContract.View {

    private StarsContract.Presenter mPresenter;

    private StarListAdapter mStarListAdapter;


    @BindView(R.id.stars_list)
    RecyclerView mStarList;


    public static StarsFragment getInstance() {
        StarsFragment starsFragment = new StarsFragment();
       /* Bundle args = new Bundle();
        args.putString(EXTRA_VIDEO_ID, videoId);
        videoDetailFragment.setArguments(args);*/
        return starsFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_stars, container, false);
        ButterKnife.bind(this, view);

        //View view = inflater.inflate(R.layout.frag_main_grid, container, false);

        mStarListAdapter = new StarListAdapter(new ArrayList<StarsItem>(),getContext());

        mStarListAdapter.setOnStarItemClickListener(new OnItemClickListener.OnStarItemClickListener() {
            @Override
            public void onItemClick(StarsItem item) {
                Intent intent = new Intent(getContext(), StarDetailActivity.class);

                intent.putExtra(EXTRA_STAR, item.getStar().getStarName());

                getContext().startActivity(intent);
            }
        });

        mStarList.setAdapter(mStarListAdapter);

        // mLatestPostList.setLayoutManager(new LinearLayoutManager(mLatestPostList.getContext()));
        mStarList.setLayoutManager(new GridLayoutManager(mStarList.getContext(), 2));


        return  view;
    }

    @Override
    public void setPresenter(StarsContract.Presenter presenter) {

        mPresenter = presenter;
    }

    @Override
    public void updateData(List<StarsItem> starsItems) {
        // mTextView.setText(videosItemList.get(0).getTitle());

        mStarListAdapter.updateData(starsItems);
    }
}

package com.example.longyuan.hubtraffic.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.longyuan.hubtraffic.R;
import com.example.longyuan.hubtraffic.pojo.video.VideosItem;
import com.example.longyuan.hubtraffic.star.StarsActivity;
import com.example.longyuan.hubtraffic.util.OnItemClickListener;
import com.example.longyuan.hubtraffic.util.VideoListAdapter;
import com.example.longyuan.hubtraffic.util.VideoListGridAdapter;
import com.example.longyuan.hubtraffic.util.enums.SearchOrdering;
import com.example.longyuan.hubtraffic.videodetail.VideoDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.longyuan.hubtraffic.main.MainActivity.EXTRA_TAG;
import static com.example.longyuan.hubtraffic.star.StarsActivity.EXTRA_STAR;
import static com.example.longyuan.hubtraffic.util.enums.SearchOrdering.FEATURED;
import static com.example.longyuan.hubtraffic.util.enums.SearchOrdering.MOSTVIEWED;
import static com.example.longyuan.hubtraffic.util.enums.SearchOrdering.NEWEST;
import static com.example.longyuan.hubtraffic.util.enums.SearchOrdering.RATING;
import static com.example.longyuan.hubtraffic.videodetail.VideoDetailActivity.EXTRA_VIDEO_ID;

/**
 * Created by LONGYUAN on 2018/2/7.
 */

public class MainFragment extends Fragment implements MainContract.View {


    private MainContract.Presenter mPresenter;

    private VideoListAdapter mVideoListAdapter;

    //private VideoListGridAdapter mVideoListGridAdapter;

    @BindView(R.id.videos_list)
    RecyclerView mLatestPostList;

    // @BindView(R.id.videos_list)
    // GridView mLatestPostList;

    @BindView(R.id.videos_swipe)
    SwipeRefreshLayout mSwipeRefreshLayout;

   @BindView(R.id.spinner_ordering)
    Spinner mSpinner;

   private String mTag;

    private String mStarName;


    private String mCategory;

    public static MainFragment getInstance() {
        return new MainFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start(mTag,mCategory,mStarName);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.frag_main, container, false);

        //View view = inflater.inflate(R.layout.frag_main_grid, container, false);

        ButterKnife.bind(this, view);
        // TODO Use fields...

      /*  mVideoListGridAdapter =new VideoListGridAdapter(getContext(),new ArrayList<VideosItem>());

        mVideoListGridAdapter.setOnVideoItemClickListener(new OnItemClickListener.OnVideoItemClickListener() {
            @Override
            public void onItemClick(VideosItem item) {
                Intent intent = new Intent(getContext(),VideoDetailActivity.class);

                intent.putExtra(EXTRA_VIDEO_ID,item.getVideoId());

                getContext().startActivity(intent);
            }
        });

        mLatestPostList.setAdapter(mVideoListGridAdapter);*/

        //mLatestPostList.setNumColumns(1);

        mVideoListAdapter = new VideoListAdapter(new ArrayList<VideosItem>(), getContext());

        mLatestPostList.setAdapter(mVideoListAdapter);

        // mLatestPostList.setLayoutManager(new LinearLayoutManager(mLatestPostList.getContext()));
        mLatestPostList.setLayoutManager(new GridLayoutManager(mLatestPostList.getContext(), 2));

        mVideoListAdapter.setOnVideoItemClickListener(new OnItemClickListener.OnVideoItemClickListener() {
            @Override
            public void onItemClick(VideosItem item) {
                Intent intent = new Intent(getContext(), VideoDetailActivity.class);

                intent.putExtra(EXTRA_VIDEO_ID, item.getVideoId());

                getContext().startActivity(intent);
            }
        });

        setSwipeRefresh();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.ordering_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        mSpinner.setAdapter(adapter);




        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                SearchOrdering searchOrdering = new SearchOrdering(FEATURED);

                switch (i) {
                    case 0:
                        searchOrdering.setOrdering(FEATURED);
                        break;
                    case 1:
                        searchOrdering.setOrdering(NEWEST);
                        break;
                    case 2:
                        searchOrdering.setOrdering(MOSTVIEWED);
                        break;
                    case 3:
                        searchOrdering.setOrdering(RATING);
                        break;
                    default:
                        break;
                }


                if(mSpinner.isSelected()) {
                    mPresenter.loadVideos(searchOrdering);
                }
                mSpinner.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if (getArguments() != null) {
            mTag = getArguments().getString(EXTRA_TAG);
            mStarName = getArguments().getString(EXTRA_STAR);
        }

        // mSpinner.
        //
        //


        setHasOptionsMenu(true);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {

        mPresenter = presenter;
    }

    private void setSwipeRefresh() {

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                mPresenter.refreshVideos(null);
            }
        });


    }


    @Override
    public void updateData(List<VideosItem> videosItemList) {
        // mTextView.setText(videosItemList.get(0).getTitle());

        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }

        mVideoListAdapter.updateData(videosItemList);
    }

    @Override
    public void errorToast(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.main_frag, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_test) {
            Intent intent = new Intent(getContext(), StarsActivity.class);

            //intent.putExtra(EXTRA_TAG, tag.getTagName());

            getContext().startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package com.example.longyuan.hubtraffic.videodetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.longyuan.hubtraffic.App;
import com.example.longyuan.hubtraffic.R;
import com.example.longyuan.hubtraffic.datastore.DataStore;
import com.example.longyuan.hubtraffic.datastore.VideoRepository;
import com.example.longyuan.hubtraffic.pojo.video.VideosItem;
import com.example.longyuan.hubtraffic.videodetail.dummy.DummyContent;
import com.example.longyuan.hubtraffic.videodetail.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.example.longyuan.hubtraffic.videodetail.VideoDetailActivity.EXTRA_VIDEO_ID;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class VideoDetailTabVideosFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private String mVideo_Id ;
    //private OnListFragmentInteractionListener mListener;

    @Inject
    protected VideoRepository mVideoRepository;

    private VideoDetailTabVideosAdapter mVideoDetailTabVideosAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public VideoDetailTabVideosFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static VideoDetailTabVideosFragment newInstance(String videoId) {
        VideoDetailTabVideosFragment fragment = new VideoDetailTabVideosFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_VIDEO_ID, videoId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();

        loadData();
    }

    private void loadData() {

        mVideoRepository.loadVideos(new DataStore.LoadVideosCallback() {
            @Override
            public void onVideosLoaded(List<VideosItem> videosItems) {
                mVideoDetailTabVideosAdapter.updateData(videosItems);

            }

            @Override
            public void onError(String error) {

            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mVideo_Id = getArguments().getString(EXTRA_VIDEO_ID);
        }


        App.getAppComponent().inject(this);
/*        DaggerVideoDetailComponent.builder()
                .appComponent(((App)getActivity().getApplication()).getAppComponent())
                .build()
                .inject(this);*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_videodetail_tab_videos_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
           /* if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {*/
                recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
          //  }

            mVideoDetailTabVideosAdapter = new VideoDetailTabVideosAdapter(new ArrayList<VideosItem>(), new OnListFragmentInteractionListener() {
                @Override
                public void onListFragmentInteraction(VideosItem item) {
                    Intent intent = new Intent(getContext(), VideoDetailActivity.class);

                    intent.putExtra(EXTRA_VIDEO_ID, item.getVideoId());

                    getContext().startActivity(intent);

                }
            },getContext());

            recyclerView.setAdapter(mVideoDetailTabVideosAdapter);
        }
        return view;
    }

/*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
   public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(VideosItem item);
    }
}

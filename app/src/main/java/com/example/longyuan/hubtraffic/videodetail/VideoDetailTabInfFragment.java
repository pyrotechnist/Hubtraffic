package com.example.longyuan.hubtraffic.videodetail;


import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.longyuan.hubtraffic.App;
import com.example.longyuan.hubtraffic.R;
import com.example.longyuan.hubtraffic.datastore.DataStore;
import com.example.longyuan.hubtraffic.datastore.VideoRepository;
import com.example.longyuan.hubtraffic.main.MainActivity;
import com.example.longyuan.hubtraffic.pojo.video.CategoriesItem;
import com.example.longyuan.hubtraffic.pojo.video.PornstarsItem;
import com.example.longyuan.hubtraffic.pojo.video.TagsItem;
import com.example.longyuan.hubtraffic.pojo.video.VideosItem;
import com.google.android.flexbox.FlexboxLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.longyuan.hubtraffic.main.MainActivity.EXTRA_CATEGORY;
import static com.example.longyuan.hubtraffic.main.MainActivity.EXTRA_STAR;
import static com.example.longyuan.hubtraffic.main.MainActivity.EXTRA_TAG;
import static com.example.longyuan.hubtraffic.videodetail.VideoDetailActivity.EXTRA_VIDEO_ID;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoDetailTabInfFragment extends Fragment {


//    @BindView(R.id.category_container_layout)
//    LinearLayout mLinearLayout_category;
//
//    @BindView(R.id.tag_container_layout)
//    LinearLayout mLinearLayout_tag;

    @BindView(R.id.category_container_layout2)
    FlexboxLayout mFlexboxLayout_Category;

    @BindView(R.id.tag_container_layout2)
    FlexboxLayout mLinearLayout_tag2;

    @BindView(R.id.star_container_layout)
    FlexboxLayout mFlexboxLayout_star;


    @BindView(R.id.video_detail_star)
    TextView mTextView_Star;

    private String mVideo_Id ;

    @Inject
    protected VideoRepository mVideoRepository;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment UserDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VideoDetailTabInfFragment newInstance(String videoId) {
        VideoDetailTabInfFragment fragment = new VideoDetailTabInfFragment();

        Bundle args = new Bundle();
        args.putString(EXTRA_VIDEO_ID, videoId);
        fragment.setArguments(args);
        return fragment;
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
        // Inflate the layout for this fragment
        View rootView =inflater.inflate(R.layout.frag_video_detail_tab_inf, container, false);

        ButterKnife.bind(this, rootView);


        loadCategory();

        return rootView;
    }

    private void loadCategory() {

        mVideoRepository.loadVideo(new DataStore.LoadVideoItemCallback() {
            @Override
            public void onVideoItemLoaded(VideosItem videosItem) {
                addCategory(videosItem.getCategories());
                addTag(videosItem.getTags());
                addStars(videosItem.getPornstars());
            }

            @Override
            public void onError(String error) {

            }
        },mVideo_Id);
    }

    private void addStars(List<PornstarsItem> pornstars) {

        for (PornstarsItem pornstarsItem : pornstars) {

            TextView textView = new TextView(getContext());

            textView.setText(pornstarsItem.getPornstarName());

            textView.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

            textView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.lightGray));

            LinearLayout.LayoutParams textViewLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            textViewLayoutParams.setMargins(10, 5, 10, 5);

            textView.setLayoutParams(textViewLayoutParams);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(getContext(), MainActivity.class);

                    intent.putExtra(EXTRA_STAR, pornstarsItem.getPornstarName());

                    getContext().startActivity(intent);
                }
            });

            mFlexboxLayout_star.addView(textView);
        }

    }

    private void addCategory(List<CategoriesItem> categories) {
        for (CategoriesItem categoriesItem : categories) {

            TextView textView = new TextView(getContext());


            textView.setText(categoriesItem.getCategory());

            LinearLayout.LayoutParams textViewLayoutParams =  new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            textViewLayoutParams.setMargins(10,5,10,5);

            textView.setLayoutParams(textViewLayoutParams);

            //textView.setBackgroundResource(R.color.solid_red);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(getContext(), MainActivity.class);

                    intent.putExtra(EXTRA_CATEGORY, categoriesItem.getCategory());

                    getContext().startActivity(intent);
                }
            });

            mFlexboxLayout_Category.addView(textView);
            //mLinearLayout_category.addView(textView);
        }
    }

    private void addTag(List<TagsItem> tags) {
        for (TagsItem tag : tags) {

            TextView textView = new TextView(getContext());

            textView.setText(tag.getTagName());

            LinearLayout.LayoutParams textViewLayoutParams =  new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            textViewLayoutParams.setMargins(10,5,10,5);

            textView.setLayoutParams(textViewLayoutParams);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(getContext(), MainActivity.class);

                    intent.putExtra(EXTRA_TAG, tag.getTagName());

                    getContext().startActivity(intent);
                }
            });


            //mLinearLayout_tag.addView(textView);

            mLinearLayout_tag2.addView(textView);
        }
    }

}

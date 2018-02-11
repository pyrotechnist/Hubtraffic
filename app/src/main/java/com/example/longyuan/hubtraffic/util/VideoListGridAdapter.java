package com.example.longyuan.hubtraffic.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.longyuan.hubtraffic.R;
import com.example.longyuan.hubtraffic.pojo.video.VideosItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LONGYUAN on 2018/2/9.
 */

public class VideoListGridAdapter extends BaseAdapter {

    private List<VideosItem> mVideosItemList;

    private Context mContext;

    private OnItemClickListener.OnVideoItemClickListener mOnVideoItemClickListener;

    public VideoListGridAdapter(Context context,List<VideosItem> videosItems) {
        mContext = context;
        mVideosItemList = videosItems;
    }

    @Override
    public int getCount() {
        return mVideosItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return mVideosItemList.size();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        //View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_item,viewGroup,false);

        ViewHolder holder;

        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_item,viewGroup,false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }


        final VideosItem aPost = mVideosItemList.get(position);

        holder.aTextView_title.setText(aPost.getTitle());

        Glide.with(mContext).load(aPost.getDefaultThumb()).into(holder.aImageView);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnVideoItemClickListener.onItemClick(aPost);
            }
        });


        return view;
    }

    public void updateData(List<VideosItem> videosItems){

        mVideosItemList = videosItems;
        notifyDataSetChanged();
    }

    public void setOnVideoItemClickListener(OnItemClickListener.OnVideoItemClickListener onVideoItemClickListener) {
        mOnVideoItemClickListener = onVideoItemClickListener;
    }


    static class ViewHolder {

        @BindView(R.id.video_item_id)
        TextView aTextView_title;


        @BindView(R.id.video_image)
        ImageView aImageView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

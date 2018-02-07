package com.example.longyuan.hubtraffic.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.longyuan.hubtraffic.R;
import com.example.longyuan.hubtraffic.pojo.video.VideosItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LONGYUAN on 2018/2/7.
 */

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.VideoListViewHolder> {
    private List<VideosItem> mVideoList;

    private Context mContext;

    public VideoListAdapter(List<VideosItem> mPostList, Context mContext) {
        this.mVideoList = mPostList;
        this.mContext = mContext;
    }


    @Override
    public VideoListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item,parent,false);

        return new VideoListViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(VideoListViewHolder holder, int position) {

        final VideosItem aPost = mVideoList.get(position);
      /*  holder.textView1.setText(HtmlHelper.urlDecode(aPost.getPname()));

        holder.textView2.setText(HtmlHelper.urlDecode(aPost.getFname()));*/

        holder.aTextView_title.setText(aPost.getTitle());

        Glide.with(mContext).load(aPost.getDefaultThumb()).into(holder.aImageView);

    }

    @Override
    public int getItemCount() {
        return mVideoList.size();
    }

    public static class VideoListViewHolder extends RecyclerView.ViewHolder{

      /*  @BindView(R.id.textView)
        TextView textView1;

        @BindView(R.id.textView2)
        TextView textView2;

        @BindView(R.id.textView3)
        TextView textView3;*/

        @BindView(R.id.video_item_id)
        TextView aTextView_title;


        @BindView(R.id.video_image)
        ImageView aImageView;

        public VideoListViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }

    public void updateData(List<VideosItem> videosItems){

        mVideoList = videosItems;
        notifyDataSetChanged();
    }
}

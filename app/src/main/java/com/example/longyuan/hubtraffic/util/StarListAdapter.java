package com.example.longyuan.hubtraffic.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.longyuan.hubtraffic.R;
import com.example.longyuan.hubtraffic.pojo.star.StarsItem;
import com.example.longyuan.hubtraffic.pojo.video.VideosItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LONGYUAN on 2018/2/7.
 */

public class StarListAdapter extends RecyclerView.Adapter<StarListAdapter.StarListViewHolder> {
    private List<StarsItem> mStarList;

    private Context mContext;

    private OnItemClickListener.OnStarItemClickListener mOnStarItemClickListener;

    public StarListAdapter(List<StarsItem> mPostList, Context mContext) {
        this.mStarList = mPostList;
        this.mContext = mContext;
    }


    @Override
    public StarListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.star_item,parent,false);



        return new StarListViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(StarListViewHolder holder, int position) {

        final StarsItem aPost = mStarList.get(position);
      /*  holder.textView1.setText(HtmlHelper.urlDecode(aPost.getPname()));

        holder.textView2.setText(HtmlHelper.urlDecode(aPost.getFname()));*/


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnStarItemClickListener.onItemClick(aPost);
            }
        });



        Glide.with(mContext).load(aPost.getStar().getStarThumb()).into(holder.aImageView);

    }

    @Override
    public int getItemCount() {
        return mStarList.size();
    }


    public void setOnStarItemClickListener(OnItemClickListener.OnStarItemClickListener onVideoItemClickListener) {
        mOnStarItemClickListener = onVideoItemClickListener;
    }

    public static class StarListViewHolder extends RecyclerView.ViewHolder{



        @BindView(R.id.star_image)
        ImageView aImageView;


        public StarListViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }

    public void updateData(List<StarsItem> starsItems){

        mStarList = starsItems;
        notifyDataSetChanged();
    }
}

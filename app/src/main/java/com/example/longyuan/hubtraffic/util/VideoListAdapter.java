package com.example.longyuan.hubtraffic.util;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

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

        //holder.aVideoView.setMediaController(new MediaController(mContext));

//设置视频源播放res/raw中的文件,文件名小写字母,格式: 3gp,mp4等,flv的不一定支持;
        //Uri rawUri = Uri.parse("android.resource://" + mContext.getPackageName() + "/" + R.raw.shuai_dan_ge);
        //holder.aVideoView.setVideoURI(rawUri);

// 播放在线视频

       // Uri mVideoUri = Uri.parse(aPost.getUrl());
        //holder.aVideoView.setVideoURI(mVideoUri);
       // holder.aVideoView.setVideoPath(mVideoUri.toString());

       // holder.aVideoView.start();
       // holder.aVideoView.requestFocus();

        //声明WebSettings子类
       /* WebSettings webSettings = holder.aVideoView.getSettings();

//如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);

        holder.aVideoView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        //holder.aVideoView.loadUrl(aPost.getUrl());

        holder.aVideoView.loadData("<iframe src=\""+  aPost.getUrl()  +"\" frameborder=\"0\" width=\"608\" height=\"468\" scrolling=\"no\"></iframe>","text/html", "utf-8");*/


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

/*
        @BindView(R.id.video_video)
        WebView aVideoView;
*/

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

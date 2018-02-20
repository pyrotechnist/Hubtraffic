package com.example.longyuan.hubtraffic.videodetail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.longyuan.hubtraffic.R;

import com.example.longyuan.hubtraffic.pojo.video.VideosItem;
import com.example.longyuan.hubtraffic.videodetail.dummy.DummyContent.DummyItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link VideoDetailTabVideosFragment.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class VideoDetailTabVideosAdapter extends RecyclerView.Adapter<VideoDetailTabVideosAdapter.ViewHolder> {

    private  List<VideosItem> mValues;

    private final Context mContext;

    private final VideoDetailTabVideosFragment.OnListFragmentInteractionListener mListener;

    public VideoDetailTabVideosAdapter(List<VideosItem> items, VideoDetailTabVideosFragment.OnListFragmentInteractionListener listener,Context context) {
        mValues = items;
        mListener = listener;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.frag_videodetail_tab_videos, parent, false);*/

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final VideosItem aPost = mValues.get(position);
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

      /*  holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnVideoItemClickListener.onItemClick(aPost);
            }
        });
*/


        Glide.with(mContext).load(aPost.getDefaultThumb()).into(holder.aImageView);


       /* holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getTitle());
        holder.mContentView.setText(mValues.get(position).getDuration());*/

       holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(aPost);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void updateData(List<VideosItem> videosItems){

        mValues = videosItems;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       /* public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public VideosItem mItem;
*/
        @BindView(R.id.video_item_id)
        TextView aTextView_title;


        @BindView(R.id.video_image)
        ImageView aImageView;


        public ViewHolder(View view) {
            super(view);
         /*   mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);*/

            ButterKnife.bind(this,view);
        }

    }
}

package com.example.longyuan.hubtraffic.util;


import com.example.longyuan.hubtraffic.pojo.category.Category;
import com.example.longyuan.hubtraffic.pojo.star.StarsItem;
import com.example.longyuan.hubtraffic.pojo.video.VideosItem;

/**
 * Created by LONGYUAN on 2018/1/12.
 */


public interface OnItemClickListener {

    interface OnVideoItemClickListener{

        void onItemClick(VideosItem item);

       // void onItemLongClick(NewlistItem item, int position);

    }

    interface OnStarItemClickListener{

        void onItemClick(StarsItem item);

        // void onItemLongClick(NewlistItem item, int position);

    }

    interface OnCategoryItemClickListener{

        void onItemClick(Category item);

        // void onItemLongClick(NewlistItem item, int position);

    }

   /* interface OnCommentItemClickListener{

        void onItemClick(CommentItem item);

        void onItemLongClick(CommentItem item, int position);
    }*/
}
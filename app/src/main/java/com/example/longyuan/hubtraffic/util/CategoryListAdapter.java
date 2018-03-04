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
import com.example.longyuan.hubtraffic.pojo.category.Category;
import com.example.longyuan.hubtraffic.pojo.video.VideosItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LONGYUAN on 2018/2/7.
 */

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoryListViewHolder> {
    private List<Category> mCategoryList;

    private Context mContext;

    private OnItemClickListener.OnCategoryItemClickListener mOnCategoryItemClickListener;

    public CategoryListAdapter(List<Category> categories, Context mContext) {
        this.mCategoryList = categories;
        this.mContext = mContext;
    }


    @Override
    public CategoryListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);



        return new CategoryListViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(CategoryListViewHolder holder, int position) {

        final Category category = mCategoryList.get(position);

        holder.textView_title.setText(category.getCategory());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnCategoryItemClickListener.onItemClick(category);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }


    public void setOnCategoryItemClickListener(OnItemClickListener.OnCategoryItemClickListener onCategoryItemClickListener) {
        mOnCategoryItemClickListener = onCategoryItemClickListener;
    }

    public static class CategoryListViewHolder extends RecyclerView.ViewHolder{

       @BindView(R.id.category_title)
        TextView textView_title;


        public CategoryListViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }

    public void updateData(List<Category> videosItems){

        mCategoryList = videosItems;
        notifyDataSetChanged();
    }
}

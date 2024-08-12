package com.example.ensayopruebabg2.platform.views.common;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ensayopruebabg2.R;
import com.example.ensayopruebabg2.data.entity.PostEntity;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

public class PostSelectAdapter extends RecyclerView.Adapter<PostSelectAdapter.ViewHolder> {
    private List<PostEntity> list;
    private Context context;
    private final OnItemClickListener itemClickListener;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvIdSelect;
        final TextView tvId;
        final TextView tvTitleSelect;
        final TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdSelect = itemView.findViewById(R.id.tvIdSelect);
            tvId = itemView.findViewById(R.id.tvId);
            tvTitleSelect = itemView.findViewById(R.id.tvTitleSelect);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }


    public PostSelectAdapter(List<PostEntity> list, Context context, OnItemClickListener itemClickListener) {
        this.list = list;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder vh, int position) {
        try {
            vh.tvId.setVisibility(View.VISIBLE);
            vh.tvId.setText("");
            vh.tvTitle.setVisibility(View.VISIBLE);
            vh.tvTitle.setText("");

            PostEntity post = list.get(position);

            vh.tvId.setText(MessageFormat.format("{0}", post.getId()));
            vh.tvTitle.setText(MessageFormat.format("{0}", post.getTitle()));

            vh.itemView.setOnClickListener(v -> {
                itemClickListener.onItemClick(list.get(position), position);
            });
        } catch (Exception exception){
            Log.e("error : ", Objects.requireNonNull(exception.getMessage()));
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public interface OnItemClickListener {
        void onItemClick(PostEntity post, int position);
    }
}

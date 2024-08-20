package com.example.ensayopruebabg2.platform.views.common;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ensayopruebabg2.R;
import com.example.ensayopruebabg2.data.entity.CommentEntity;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

public class CommentPostAdapter extends RecyclerView.Adapter<CommentPostAdapter.ViewHolder> {

    private List<CommentEntity> list;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvName;
        final TextView tvEmail;
        final TextView tvBody;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvBody = itemView.findViewById(R.id.tvBody);
        }
    }

    public CommentPostAdapter(List<CommentEntity> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comments, parent,false);
        return new CommentPostAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
//            holder.tvName.setVisibility(View.VISIBLE);
            holder.tvName.setText("");
//            holder.tvEmail.setVisibility(View.VISIBLE);
            holder.tvEmail.setText("");
//            holder.tvBody.setVisibility(View.VISIBLE);
            holder.tvBody.setText("");

            CommentEntity comment = list.get(position);

            holder.tvName.setText(MessageFormat.format("{0}", comment.getName()));
            holder.tvEmail.setText(MessageFormat.format("{0}", comment.getEmail()));
            holder.tvBody.setText(MessageFormat.format("{0}", comment.getBody()));

        } catch (Exception exception){
            Log.e("error : ", Objects.requireNonNull(exception.getMessage()));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

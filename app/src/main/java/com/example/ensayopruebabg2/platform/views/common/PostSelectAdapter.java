package com.example.ensayopruebabg2.platform.views.common;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.caverock.androidsvg.SVG;
import com.example.ensayopruebabg2.R;
import com.example.ensayopruebabg2.domain.model.PostModel;

import java.net.URL;
import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;


public class PostSelectAdapter extends RecyclerView.Adapter<PostSelectAdapter.ViewHolder> {
    private List<PostModel> list;
    private Context context;
    private final OnItemClickListener itemClickListener;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvBody;
        final TextView tvTitle;
        final ImageView imageViewProduct;
        final AppCompatTextView tvSeeMore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewProduct = itemView.findViewById(R.id.tvImgPost);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvSeeMore =  itemView.findViewById(R.id.tvSeeMore);
        }
    }


    public PostSelectAdapter(List<PostModel> list, Context context, OnItemClickListener itemClickListener) {
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
            vh.tvBody.setVisibility(View.VISIBLE);
            vh.tvBody.setText("");
            vh.tvTitle.setVisibility(View.VISIBLE);
            vh.tvTitle.setText("");
            vh.tvSeeMore.setVisibility(View.GONE);
            vh.tvSeeMore.setText("");

            PostModel post = list.get(position);

            String title = post.getTitle().substring(0, 1).toUpperCase() + post.getTitle().substring(1).toLowerCase();
            vh.tvTitle.setText(MessageFormat.format("{0}", title));
            vh.tvBody.setText(MessageFormat.format("{0}", post.getBody()));

            if (post.getImg()!= null){
                new Thread(() -> {
                    try {
                        SVG svg = SVG.getFromInputStream(new URL(post.getImg()).openStream());
                        ((Activity) context).runOnUiThread(() -> {
                            vh.imageViewProduct.setImageDrawable(new PictureDrawable(svg.renderToPicture()));
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }

            // Verificar si el texto ocupa mas de una línea
            vh.tvBody.post(() -> {
                if (vh.tvBody.getLineCount() > 1) {
                    vh.tvSeeMore.setText("Ver mas");
                    vh.tvSeeMore.setVisibility(View.VISIBLE);

                    ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) vh.tvBody.getLayoutParams();
                    params.setMarginEnd(10);
                    vh.tvBody.setLayoutParams(params);

                    vh.tvBody.setMaxLines(1); // Mostrar solo una línea
                }
            });

            // Configurar el clic en "ver más"
            vh.tvSeeMore.setOnClickListener(v -> {
                if (vh.tvBody.getMaxLines() == 1) {
                    vh.tvBody.setMaxLines(10); // Mostrar todo el texto
                    vh.tvSeeMore.setText("Ver menos");
                } else {
                    vh.tvBody.setMaxLines(1); // Mostrar solo una línea
                    vh.tvSeeMore.setText("Ver menos");
                }
            });

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
        void onItemClick(PostModel post, int position);
    }
}

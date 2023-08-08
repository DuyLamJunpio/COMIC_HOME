package com.example.asm_android_networking.Fragment.Comic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_android_networking.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterRead extends RecyclerView.Adapter<AdapterRead.RecyclerViewHolder> {

    private List<String> list;

    public void setData(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutitem_chapter, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        final String data = list.get(position);
        if (data == null) {
            return;
        }

        Picasso.get().load(data).fit().into(holder.imgItemChapter);

    }



    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgItemChapter;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            imgItemChapter = itemView.findViewById(R.id.imgItemChapter);

        }
    }
}

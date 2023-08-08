package com.example.asm_android_networking.Fragment.Comic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asm_android_networking.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ComicGridAdapter extends BaseAdapter {
    private Context context;
    private List<Data> list = new ArrayList<>();
    private int layout;

    public ComicGridAdapter(Context context, List<Data> list, int layout) {
        this.context = context;
        this.list = list;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IconViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new IconViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder.tvItemNameComic = convertView.findViewById(R.id.tvItemNameComic);
            viewHolder.imgItemAvtComic = convertView.findViewById(R.id.imgItemAvtComic);
            viewHolder.idLayoutitemComic = convertView.findViewById(R.id.idLayoutitemComic);

            Picasso.get().load(list.get(position).getAvatar()).fit().into(viewHolder.imgItemAvtComic);

            viewHolder.tvItemNameComic.setText(list.get(position).getTitle());

            viewHolder.idLayoutitemComic.setOnClickListener(v -> {
                Intent intent = new Intent(context, ReadComicActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("idComic1", list.get(position).getId());
                intent.putExtras(bundle);
                context.startActivity(intent);
            });

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (IconViewHolder) convertView.getTag();
        }
        return convertView;
    }

    public static class IconViewHolder {
        private TextView tvItemNameComic;
        private ImageView imgItemAvtComic;

        private LinearLayout idLayoutitemComic;
    }
}

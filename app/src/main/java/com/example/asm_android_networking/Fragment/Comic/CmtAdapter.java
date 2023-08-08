package com.example.asm_android_networking.Fragment.Comic;

import static com.example.asm_android_networking.Login.LoginFragment.ID;
import static com.example.asm_android_networking.RetrofitRequest.getRetrofit;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_android_networking.R;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CmtAdapter extends RecyclerView.Adapter<CmtAdapter.RecyclerViewHolder> {

    private List<CmtModel.Data> list;

    private InterClickItemData interClickItemData;

    public interface InterClickItemData {
        void updateData(CmtModel.Data data);

        void deleteData(CmtModel.Data data);
    }

    public CmtAdapter(InterClickItemData interClickItemData) {
        this.interClickItemData = interClickItemData;
    }

    private ComicDAO comicDAO = getRetrofit().create(ComicDAO.class);
    ;

    public void setData(List<CmtModel.Data> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutitem_cmt, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        final CmtModel.Data data = list.get(position);
        if (data == null) {
            return;
        }

        Call<UserModel> call = comicDAO.getUserById(data.getUser());
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                Picasso.get().load(response.body().getData().getImg()).fit().into(holder.imgItemAvt);
                holder.tvItemName.setText(response.body().getData().getFullname());
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });

        holder.tvItemCmt.setText(data.getText());
        holder.tvItemTime.setText(data.getTime());

        if (!ID.equals(data.getUser())) {
            holder.imgItemEdit.setVisibility(View.GONE);
            holder.imgItemDelete.setVisibility(View.GONE);
        }else{
            holder.imgItemEdit.setVisibility(View.VISIBLE);
            holder.imgItemDelete.setVisibility(View.VISIBLE);
        }

        holder.imgItemEdit.setOnClickListener(v -> {
            interClickItemData.updateData(data);
        });

        holder.imgItemDelete.setOnClickListener(v -> {
            interClickItemData.deleteData(data);

        });

    }


    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgItemAvt, imgItemEdit, imgItemDelete;
        private TextView tvItemCmt, tvItemName, tvItemTime;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            imgItemAvt = itemView.findViewById(R.id.imgItemAvt);
            tvItemCmt = itemView.findViewById(R.id.tvItemCmt);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvItemTime = itemView.findViewById(R.id.tvItemTime);
            imgItemEdit = itemView.findViewById(R.id.imgItemEdit);
            imgItemDelete = itemView.findViewById(R.id.imgItemDelete);

        }
    }
}


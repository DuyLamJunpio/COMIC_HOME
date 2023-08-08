package com.example.asm_android_networking.Fragment.Comic;

import static com.example.asm_android_networking.Login.LoginFragment.ID;
import static com.example.asm_android_networking.Login.LoginFragment.TOKEN;
import static com.example.asm_android_networking.RetrofitRequest.getRetrofit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.asm_android_networking.databinding.ActivityReadComicBinding;

import com.example.asm_android_networking.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadComicActivity extends AppCompatActivity {

    private ActivityReadComicBinding binding;

    private CmtAdapter adapter;

    private ComicDAO comicDAO;

    private ComicDetailModel.Data dataCmt;

    Date currentDate = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReadComicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        comicDAO = getRetrofit().create(ComicDAO.class);
        addCmt();
        adapter = new CmtAdapter(new CmtAdapter.InterClickItemData() {
            @Override
            public void updateData(CmtModel.Data data) {
                updateCar(data, dataCmt.get_id());
            }

            @Override
            public void deleteData(CmtModel.Data data) {
                deleteCar(data,dataCmt.get_id());
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.rcvCmt.setLayoutManager(manager);
        binding.rcvCmt.setAdapter(adapter);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String id = bundle.getString("idComic1");
            Call<ComicDetailModel> call = comicDAO.getDataById(id);
            call.enqueue(new Callback<ComicDetailModel>() {
                @Override
                public void onResponse(Call<ComicDetailModel> call, Response<ComicDetailModel> response) {
                    ComicDetailModel.Data data = response.body().getData();
                    dataCmt = data;
                    loadCmt(data.get_id());
                    binding.tvTitle.setText(data.getTitle());
                    binding.tvAuthor.setText(data.getAuthor());
                    binding.tvDate.setText(data.getDate());
                    binding.tvChapter.setText(String.valueOf(data.getChapters().length));
                    binding.tvContent.setText(data.getContent());
                    Picasso.get().load(data.getAvatar()).fit().into(binding.imgAvt);
                    binding.btnReadComic.setOnClickListener(v -> {
                        Intent intent = new Intent(getApplicationContext(), ChapterActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putStringArray("chapter", data.getChapters());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    });
                }

                @Override
                public void onFailure(Call<ComicDetailModel> call, Throwable t) {
                    Log.e("RetrofitError", "Error: " + t.getMessage());
                }
            });
        }
    }

    private void addCmt() {
        binding.edAddCmt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    String content = binding.edAddCmt.getText().toString();

                    // Đảm bảo bạn đã khởi tạo currentDate một cách chính xác
                    Date currentDate = new Date();

                    Call<CmtModel> call = comicDAO.postCmt(new CmtModel.Data(content, String.valueOf(currentDate), dataCmt.get_id(), ID));
                    call.enqueue(new Callback<CmtModel>() {
                        @Override
                        public void onResponse(Call<CmtModel> call, Response<CmtModel> response) {
                            if (response.isSuccessful()) {
                                //Toast.makeText(getApplicationContext(), "Gửi dữ liệu thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                try {
                                    String errorBody = response.errorBody().string();
                                    //Toast.makeText(getApplicationContext(), errorBody, Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<CmtModel> call, Throwable t) {
                            Log.d("ERROR!!!", t.getMessage());
                            loadCmt(dataCmt.get_id());
                            binding.edAddCmt.setText("");
                        }
                    });
                    return true;
                }
                return false;
            }
        });
    }


    private void updateCar(CmtModel.Data data, String id) {
        Dialog dialog = new Dialog(ReadComicActivity.this);
        dialog.setContentView(R.layout.layoutedit);
        dialog.show();
        EditText edEditCmt = dialog.findViewById(R.id.edEditCmt);
        Button btnUpdate = dialog.findViewById(R.id.btnUpdate);
        if (data != null) {
            edEditCmt.setText(data.getText());
        }
        btnUpdate.setOnClickListener(v -> {
            Log.d("TAG", String.valueOf(currentDate));
            String cmt = edEditCmt.getText().toString();
            Call<CmtModel> call = comicDAO.editById(data.get_id(), new CmtModel.Data(cmt, String.valueOf(currentDate), data.getComic(), data.getUser()), TOKEN);
            call.enqueue(new Callback<CmtModel>() {
                @Override
                public void onResponse(Call<CmtModel> call, Response<CmtModel> response) {
                    Toast.makeText(getApplicationContext(), "Cập nhật liệu thành công", Toast.LENGTH_SHORT).show();
                    loadCmt(id);
                    dialog.dismiss();
                }

                @Override
                public void onFailure(Call<CmtModel> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void deleteCar(CmtModel.Data data, String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ReadComicActivity.this);
        builder.setTitle("Confirm Delete");
        String message = "Are you sure to delete ?";
        builder.setMessage(message);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Call<CmtModel> call = comicDAO.deleteById(data.get_id(), TOKEN);
                call.enqueue(new Callback<CmtModel>() {
                    @Override
                    public void onResponse(Call<CmtModel> call, Response<CmtModel> response) {
                        Toast.makeText(getApplicationContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                        loadCmt(id);
                    }

                    @Override
                    public void onFailure(Call<CmtModel> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        builder.setNegativeButton("NO", null);
        builder.show();
    }

    private void loadCmt(String id) {
        Call<CmtModel> callCmt = comicDAO.getCmtById(id);
        callCmt.enqueue(new Callback<CmtModel>() {
            @Override
            public void onResponse(Call<CmtModel> call, Response<CmtModel> response) {
                adapter.setData(Arrays.asList(response.body().getData()));
            }

            @Override
            public void onFailure(Call<CmtModel> call, Throwable t) {
                Log.e("RetrofitError322222222222", "Error: " + t.getMessage());
            }
        });
    }
}
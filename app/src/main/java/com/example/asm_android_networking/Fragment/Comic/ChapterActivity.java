package com.example.asm_android_networking.Fragment.Comic;

import static com.example.asm_android_networking.RetrofitRequest.getRetrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.asm_android_networking.R;
import com.example.asm_android_networking.databinding.ActivityChapterBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChapterActivity extends AppCompatActivity {

    private ActivityChapterBinding binding;

    private AdapterRead adapterRead;

    private List<String> chapterTitles;

    private ComicDAO comicDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChapterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        comicDAO = getRetrofit().create(ComicDAO.class);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String[] arrChapter = bundle.getStringArray("chapter");
            chapterTitles = new ArrayList<>();
            List<String> list = new ArrayList<>();
            for (int i = 0; i < arrChapter.length; i++) {
                int j = i + 1;
                chapterTitles.add("Chapter " +j+ "");
                Log.d("TAG", arrChapter[i]);
            }

            adapterRead = new AdapterRead();

            LinearLayoutManager manager = new LinearLayoutManager(this);
            binding.rcvChapter.setLayoutManager(manager);
            binding.rcvChapter.setAdapter(adapterRead);

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, chapterTitles);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.spinChapter.setAdapter(adapter);

            binding.spinChapter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("TAG", "onItemSelected: " + position);
                    Call<ChapterModel> call = comicDAO.getChapterById(arrChapter[position]);
                    call.enqueue(new Callback<ChapterModel>() {
                        @Override
                        public void onResponse(Call<ChapterModel> call, Response<ChapterModel> response) {
                            Log.d("TAG", "onResponse33333: "+response.body().getData().toString());
                            adapterRead.setData(Arrays.asList(response.body().getData().getImages()));
                        }

                        @Override
                        public void onFailure(Call<ChapterModel> call, Throwable t) {
                            Log.e("RetrofitError", "Error: " + t.getMessage());
                        }
                    });

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }
}
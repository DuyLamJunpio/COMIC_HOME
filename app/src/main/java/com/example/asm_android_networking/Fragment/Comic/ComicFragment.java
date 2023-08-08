package com.example.asm_android_networking.Fragment.Comic;


import static com.example.asm_android_networking.RetrofitRequest.getRetrofit;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.asm_android_networking.Fragment.Comic.SlideShow.SlideAdapter;
import com.example.asm_android_networking.Fragment.Comic.SlideShow.SlideModel;
import com.example.asm_android_networking.Login.LoginDAO;
import com.example.asm_android_networking.R;
import com.example.asm_android_networking.databinding.FragmentComicBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ComicFragment extends Fragment {

    private FragmentComicBinding binding;

    private SlideAdapter slideAdapter;
    private Timer timer;
    private List<SlideModel> listSlide;

    private List<Data> dataList;
    private ComicGridAdapter adapter;

    private ComicDAO comicDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentComicBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        comicDAO = getRetrofit().create(ComicDAO.class);
        dataList = new ArrayList<>();
        loadData();
    }

    public void loadData() {
        dataList.clear();
        Call<ComicModel> call = comicDAO.getData();
        call.enqueue(new Callback<ComicModel>() {
            @Override
            public void onResponse(Call<ComicModel> call, Response<ComicModel> response) {
                Log.d("ResponseData", "Raw data: " + response.body().getData().toString());
                if (response.isSuccessful() && response.body() != null && response.body().getData() != null) {
                    dataList.addAll(Arrays.asList(response.body().getData().clone()));
                    slideShow();
                    adapter = new ComicGridAdapter(getActivity(),
                            dataList, R.layout.layoutitem_comic);
                    binding.idGridViewComic.setAdapter(adapter);
                } else {
                    Log.e("RetrofitError", "Response is null or doesn't contain valid data.");
                }
            }

            @Override
            public void onFailure(Call<ComicModel> call, Throwable t) {
                Log.e("RetrofitError", "Error: " + t.getMessage());
            }
        });
    }

    private void slideShow(){
        listSlide = getListSlide();
        slideAdapter = new SlideAdapter(getActivity(),listSlide);
        binding.idViewSlide.setAdapter(slideAdapter);
        binding.idCirle.setViewPager(binding.idViewSlide);
        autoSlideImage();
    }
    private void autoSlideImage() {
        if(listSlide == null || listSlide.isEmpty() || binding.idViewSlide == null){
            return;
        }

        if(timer == null){
            timer = new Timer();
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = binding.idViewSlide.getCurrentItem();
                        int totalItem = listSlide.size() - 1;
                        if(currentItem < totalItem){
                            currentItem++;
                            binding.idViewSlide.setCurrentItem(currentItem);
                        }else{
                            binding.idViewSlide.setCurrentItem(0);
                        }
                    }
                });
            }
        },500 ,3000);
    }

    private List<SlideModel> getListSlide() {
        List<SlideModel> list = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            list.add(new SlideModel(dataList.get(i).getAvatar(),dataList.get(i).getId()));
        }
        return list;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(timer != null){
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
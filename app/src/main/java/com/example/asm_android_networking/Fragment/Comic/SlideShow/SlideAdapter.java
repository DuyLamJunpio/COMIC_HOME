package com.example.asm_android_networking.Fragment.Comic.SlideShow;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class SlideAdapter extends FragmentStateAdapter {
    private List<SlideModel> listSlide;

    public SlideAdapter(@NonNull FragmentActivity fragmentActivity, List<SlideModel> list) {
        super(fragmentActivity);
        this.listSlide = list;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        SlideModel slide = listSlide.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("idComic",slide);
        Slide_Show_Fragment slide_show_fragment = new Slide_Show_Fragment();
        slide_show_fragment.setArguments(bundle);
        return slide_show_fragment;
    }

    @Override
    public int getItemCount() {
        if(listSlide != null){
            return listSlide.size();
        }
        return 0;
    }
}

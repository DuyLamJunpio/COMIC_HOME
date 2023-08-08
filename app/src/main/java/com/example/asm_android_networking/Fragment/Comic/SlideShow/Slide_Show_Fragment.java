package com.example.asm_android_networking.Fragment.Comic.SlideShow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.asm_android_networking.R;
import com.squareup.picasso.Picasso;

public class Slide_Show_Fragment extends Fragment {
private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_slide__show_, container, false);
        // Inflate the layout for this fragment

        Bundle bundle = getArguments();
        SlideModel slide = (SlideModel) bundle.get("idComic");

        ImageView imageView = view.findViewById(R.id.id_Img1);
        Picasso.get().load(slide.getResourceID()).fit().into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), MainActivity2.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("photo",photo);
//                intent.putExtras(bundle);
//                getActivity().startActivity(intent);
            }
        });

        return view;
    }
}
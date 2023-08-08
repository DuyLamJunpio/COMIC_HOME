package com.example.asm_android_networking.Comic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;


import com.example.asm_android_networking.Fragment.AuthorFragment;
import com.example.asm_android_networking.Fragment.Comic.ComicFragment;
import com.example.asm_android_networking.Fragment.DonateFragment;
import com.example.asm_android_networking.Fragment.ProfileFragment;
import com.example.asm_android_networking.R;
import com.example.asm_android_networking.databinding.ActivityComicBinding;
import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;

public class ComicActivity extends AppCompatActivity {
    Class fragmentClass;

    public static Fragment fragment;

    private ActivityComicBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityComicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameLayout, new ComicFragment()).commit();
        initControl();
    }

    public void initControl() {

        List<MenuItem> menuItems = new ArrayList<>();

        //Use the MenuItem given by this library and not the default one.
        //First parameter is the title of the menu item and then the second parameter is the image which will be the background of the menu item.

        menuItems.add(new MenuItem("Explore Comic", R.drawable.comic_bg));
        menuItems.add(new MenuItem("Profile", R.drawable.profile_bg));
        menuItems.add(new MenuItem("Author", R.drawable.author_bg));
        menuItems.add(new MenuItem("Donate", R.drawable.donate_bg));

        //then add them to navigation drawer

        binding.navigationDrawer.setMenuItemList(menuItems);
        fragmentClass = ComicActivity.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();
        }


        //Listener to handle the menu item click. It returns the position of the menu item clicked. Based on that you can switch between the fragments.

        binding.navigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClicked(int position) {
                System.out.println("Position " + position);
                switch (position) {
                    case 0: {
                        fragmentClass = ComicFragment.class;
                        break;
                    }
                    case 1: {
                        fragmentClass = ProfileFragment.class;
                        break;
                    }
                    case 2: {
                        fragmentClass = AuthorFragment.class;
                        break;
                    }
                    case 3: {
                        fragmentClass = DonateFragment.class;
                        break;
                    }
                }

                //Listener for drawer events such as opening and closing.
                binding.navigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {

                    @Override
                    public void onDrawerOpened() {

                    }

                    @Override
                    public void onDrawerOpening() {

                    }

                    @Override
                    public void onDrawerClosing() {
                        System.out.println("Drawer closed");

                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (fragment != null) {
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();

                        }
                    }

                    @Override
                    public void onDrawerClosed() {

                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        System.out.println("State " + newState);
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
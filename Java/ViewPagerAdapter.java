package com.example.mymusicplayer;

import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    ArrayList<String> tabTitle =new ArrayList<>();
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment,String title)
    {
        this.fragmentArrayList.add(fragment);
        this.tabTitle.add(title);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    public CharSequence getPageTitle(int position) {
        return tabTitle.get(position);
    }
}

package com.example.cryptocurrency.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.cryptocurrency.fragment.TopLossGainFragment;

import java.util.ArrayList;
import java.util.List;

public class TopLossGainPagerAdapter  extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> titleList = new ArrayList<>();


    public TopLossGainPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return titleList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return (CharSequence) titleList.get(position);
    }

    public void addFragment (Fragment fragment , String title){
        fragmentList.add(fragment);
        titleList.add(title);
    }
}

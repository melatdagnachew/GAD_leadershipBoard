package com.example.leaderboard.adapters;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.leaderboard.R;
import com.example.leaderboard.ui.main.RecyclerViewFragment;


public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.learners, R.string.skill};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        return RecyclerViewFragment.newInstance(position + 1);

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }




}
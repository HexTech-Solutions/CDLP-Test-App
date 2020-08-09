package com.hextech.cdlpreptest.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hextech.cdlpreptest.R;

import ui.fragments.LearnFragment;
import ui.fragments.ReviewFragment;
import ui.fragments.SettingsFragment;
import ui.fragments.TestFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3, R.string.tab_text_4};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        //show the fragments to the tabs
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new LearnFragment();
                break;
            case 1:
                fragment = new ReviewFragment();
                break;
            case 2:
                fragment = new TestFragment();
                break;
            case 3:
                fragment = new SettingsFragment();
                break;
        }
        return fragment;

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Learn";
            case 1:
                return "Review";
            case 2:
                return "Test";
            case 3:
                return "Settings";
        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 4 total tabs.
        return 4;
    }
}
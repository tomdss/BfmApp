

package com.example.framework.mvvm.ui.feed;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.framework.mvvm.ui.feed.blogs.BlogFragment;
import com.example.framework.mvvm.ui.feed.opensource.OpenSourceFragment;

/**
 * Created by amitshekhar on 10/07/17.
 */

public class FeedPagerAdapter extends FragmentStatePagerAdapter {

    private int mTabCount;

    public FeedPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mTabCount = 0;
    }

    @Override
    public int getCount() {
        return mTabCount;
    }

    public void setCount(int count) {
        mTabCount = count;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return BlogFragment.newInstance();
            case 1:
                return OpenSourceFragment.newInstance();
            default:
                return null;
        }
    }
}

package com.example.framework.mvvm.ui.feed;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.core.app.NavUtils;
import androidx.core.app.TaskStackBuilder;

import com.google.android.material.tabs.TabLayout;
import com.example.framework.mvvm.BR;
import com.example.framework.mvvm.R;
import com.example.framework.mvvm.databinding.ActivityFeedBinding;
import com.example.framework.mvvm.di.component.ActivityComponent;
import com.example.framework.mvvm.ui.base.BaseActivity;

import javax.inject.Inject;

public class FeedActivity extends BaseActivity<ActivityFeedBinding, FeedViewModel> {

    @Inject
    FeedPagerAdapter mPagerAdapter;

    private ActivityFeedBinding mActivityFeedBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, FeedActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_feed;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Respond to the action bar's Up/Home button
        if (item.getItemId() == android.R.id.home) {
            Intent upIntent = NavUtils.getParentActivityIntent(this);
            upIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                // This activity is NOT part of this app's task, so create a new task
                // when navigating up, with a synthesized back stack.
                TaskStackBuilder.create(this)
                        // Add all of this activity's parents to the back stack
                        .addNextIntentWithParentStack(upIntent)
                        // Navigate up to the closest parent
                        .startActivities();
            } else {
                // This activity is part of this app's task, so simply
                // navigate up to the logical parent activity.
                NavUtils.navigateUpTo(this, upIntent);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityFeedBinding = getViewDataBinding();
        setUp();
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }

    private void setUp() {
        setSupportActionBar(mActivityFeedBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        mPagerAdapter.setCount(2);

        mActivityFeedBinding.feedViewPager.setAdapter(mPagerAdapter);

        mActivityFeedBinding.tabLayout.addTab(mActivityFeedBinding.tabLayout.newTab().setText(getString(R.string.blog)));
        mActivityFeedBinding.tabLayout.addTab(mActivityFeedBinding.tabLayout.newTab().setText(getString(R.string.open_source)));

        mActivityFeedBinding.feedViewPager.setOffscreenPageLimit(mActivityFeedBinding.tabLayout.getTabCount());

        mActivityFeedBinding.feedViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mActivityFeedBinding.tabLayout));

        mActivityFeedBinding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mActivityFeedBinding.feedViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
        });
    }
}

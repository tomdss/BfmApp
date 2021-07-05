package com.example.framework.mvvm.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.example.framework.mvvm.BR;
import com.example.framework.mvvm.R;
import com.example.framework.mvvm.databinding.ActivityHomeBinding;
import com.example.framework.mvvm.di.component.ActivityComponent;
import com.example.framework.mvvm.ui.base.BaseActivity;
import com.example.framework.mvvm.ui.home.news.NewsFragment;
import com.example.framework.mvvm.ui.home.profile.ProfileFragment;
import com.example.framework.mvvm.ui.home.search.SearchFragment;
import com.example.framework.mvvm.utils.ViewUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeActivity extends BaseActivity<ActivityHomeBinding, HomeViewModel> implements HomeNavigator, BottomNavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = HomeActivity.class.getSimpleName();

    private ActivityHomeBinding mBinding;
    private BottomNavigationView mBottomNavigationView;
    private SearchView mSearch;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = getViewDataBinding();
        mViewModel.setNavigator(this);
        mSearch = mBinding.searchView;
        setUp();
        showFragment(NewsFragment.newInstance());
    }

    @SuppressLint("ClickableViewAccessibility")
    private void showViewSearch(boolean b) {
        if (mSearch == null) return;
        if (!b) {
            mSearch.setVisibility(View.GONE);
            mSearch.setActivated(false);
        } else {
            mSearch.setVisibility(View.VISIBLE);
            mSearch.setActivated(true);
            mSearch.onActionViewExpanded();
            mSearch.setIconified(false);
            mSearch.requestFocus();
        }

    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();
    }

    private void setUp() {
        mBottomNavigationView = mBinding.bottomNavMenu;
        mBottomNavigationView.setItemIconTintList(null);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
        mSearch.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, "onQueryTextSubmit = " + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG, "onQueryTextSubmit = " + newText);
                return false;
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navNews:
                showFragment(NewsFragment.newInstance());
                showViewSearch(false);
                return true;
            case R.id.navSearch:
                showFragment(SearchFragment.newInstance());
                showViewSearch(true);
                return true;
            case R.id.navProfile:
                showFragment(ProfileFragment.newInstance());
                showViewSearch(false);
                return true;

        }
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
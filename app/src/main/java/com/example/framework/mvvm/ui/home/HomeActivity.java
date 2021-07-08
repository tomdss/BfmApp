package com.example.framework.mvvm.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.example.framework.mvvm.BR;
import com.example.framework.mvvm.R;
import com.example.framework.mvvm.databinding.ActivityHomeBinding;
import com.example.framework.mvvm.di.component.ActivityComponent;
import com.example.framework.mvvm.ui.base.BaseActivity;
import com.example.framework.mvvm.ui.home.listgame.ListGameFragment;
import com.example.framework.mvvm.ui.home.profile.ProfileFragment;
import com.example.framework.mvvm.ui.home.search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeActivity extends BaseActivity<ActivityHomeBinding, HomeViewModel> implements HomeNavigator, BottomNavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = HomeActivity.class.getSimpleName();

    private ActivityHomeBinding mBinding;
    private BottomNavigationView mBottomNavigationView;
    private SearchView mSearch;

    private Fragment mFragment;

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
        mSearch = mBinding.appBarNormal.searchView;
        setUp();
        mFragment = ListGameFragment.newInstance();
        showFragment(mFragment, false);
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

    public void showFragment(Fragment fragment, Boolean isSearch) {
        showViewSearch(isSearch);
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
        Boolean isSearch = false;
        switch (menuItem.getItemId()) {
            case R.id.navNews:
                mFragment = ListGameFragment.newInstance();
                break;
            case R.id.navSearch:
                mFragment = SearchFragment.newInstance();
                isSearch = true;
                break;
            case R.id.navProfile:
                mFragment = ProfileFragment.newInstance();
                break;
        }
        showFragment(mFragment, isSearch);
        return true;
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
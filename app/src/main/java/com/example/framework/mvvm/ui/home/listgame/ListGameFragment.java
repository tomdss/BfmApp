package com.example.framework.mvvm.ui.home.listgame;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.library.baseAdapters.BR;

import com.example.framework.mvvm.R;
import com.example.framework.mvvm.databinding.FragmentListGameBinding;
import com.example.framework.mvvm.di.component.FragmentComponent;
import com.example.framework.mvvm.ui.base.BaseFragment;
import com.example.framework.mvvm.ui.home.HomeActivity;
import com.example.framework.mvvm.ui.home.profile.ProfileFragment;
import com.example.framework.mvvm.ui.home.search.SearchFragment;

public class ListGameFragment extends BaseFragment<FragmentListGameBinding, ListGameViewModel> implements ListGameNavigator {

    public static final String TAG = ListGameFragment.class.getSimpleName();

    public static ListGameFragment newInstance() {

        Bundle args = new Bundle();

        ListGameFragment fragment = new ListGameFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_list_game;
    }

    @Override
    public void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.setNavigator(this);
    }

    @Override
    public void onBillionClick() {
        HomeActivity activity = (HomeActivity) getActivity();
        assert activity != null;
        activity.showFragment(ProfileFragment.newInstance(), false);
    }

    @Override
    public void onNumberClick() {
        HomeActivity activity = (HomeActivity) getActivity();
        assert activity != null;
        activity.showFragment(SearchFragment.newInstance(), true);
    }
}

package com.example.framework.mvvm.ui.home.profile;

import android.os.Bundle;

import androidx.databinding.library.baseAdapters.BR;

import com.example.framework.mvvm.R;
import com.example.framework.mvvm.databinding.FragmentProfileBinding;
import com.example.framework.mvvm.di.component.FragmentComponent;
import com.example.framework.mvvm.ui.base.BaseFragment;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding, ProfileViewModel> implements ProfileNavigator {

    public static final String TAG = ProfileFragment.class.getSimpleName();

    public static ProfileFragment newInstance() {
        Bundle args = new Bundle();
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    public void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }
}

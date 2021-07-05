

package com.example.framework.mvvm.ui.about;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.framework.mvvm.BR;
import com.example.framework.mvvm.R;
import com.example.framework.mvvm.databinding.FragmentAboutBinding;
import com.example.framework.mvvm.di.component.FragmentComponent;
import com.example.framework.mvvm.ui.base.BaseFragment;




public class AboutFragment extends BaseFragment<FragmentAboutBinding, AboutViewModel> implements AboutNavigator {

    public static final String TAG = "AboutFragment";

    public static AboutFragment newInstance() {
        Bundle args = new Bundle();
        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_about;
    }


    @Override
    public void goBack() {
        getBaseActivity().onFragmentDetached(TAG);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.setNavigator(this);
    }

    @Override
    public void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }
}

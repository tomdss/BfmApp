package com.example.framework.mvvm.ui.home.search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.library.baseAdapters.BR;

import com.example.framework.mvvm.R;
import com.example.framework.mvvm.databinding.FragmentNewsBinding;
import com.example.framework.mvvm.databinding.FragmentSearchBinding;
import com.example.framework.mvvm.di.component.FragmentComponent;
import com.example.framework.mvvm.ui.base.BaseFragment;
import com.example.framework.mvvm.utils.ViewUtils;

public class SearchFragment extends BaseFragment<FragmentSearchBinding, SearchViewModel> implements SearchNavigator {

    public static final String TAG = SearchFragment.class.getSimpleName();

    private FragmentSearchBinding mBinding;

    public static SearchFragment newInstance() {
        Bundle args = new Bundle();
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = getViewDataBinding();
        setUp();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setUp() {
        mBinding.tvv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                hideKeyboard();
                return false;
            }
        });
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }
}

package com.example.framework.mvvm.ui.home.news;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.mvvm.R;
import com.example.framework.mvvm.data.model.api.NewsItemResponse;
import com.example.framework.mvvm.databinding.FragmentNewsBinding;
import com.example.framework.mvvm.di.component.FragmentComponent;
import com.example.framework.mvvm.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

public class NewsFragment extends BaseFragment<FragmentNewsBinding, NewsViewModel> implements NewsNavigator, NewsAdapter.NewsAdapterListener {

    public static final String TAG = NewsFragment.class.getSimpleName();

    FragmentNewsBinding mFragmentNewsBinding;

    @Inject
    NewsAdapter mNewsAdapter;


    public static NewsFragment newInstance() {
        Bundle args = new Bundle();
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.setNavigator(this);
        mNewsAdapter.setListener(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentNewsBinding = getViewDataBinding();
        setUp();
    }

    private void setUp() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mFragmentNewsBinding.rvNewsList.setLayoutManager(linearLayoutManager);
        mFragmentNewsBinding.rvNewsList.setItemAnimator(new DefaultItemAnimator());
        mFragmentNewsBinding.rvNewsList.setAdapter(mNewsAdapter);

    }

    @Override
    public void handleError(Throwable throwable) {
        Log.d(TAG, throwable.getMessage());
    }

    @Override
    public void updateNewsList(List<NewsItemResponse> list) {
        mNewsAdapter.addItems(list);
    }

    @Override
    public void onRetryClick() {
        mViewModel.getDataNews();
    }
}

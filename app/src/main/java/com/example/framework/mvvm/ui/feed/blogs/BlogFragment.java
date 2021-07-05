

package com.example.framework.mvvm.ui.feed.blogs;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.framework.mvvm.data.model.api.BlogResponse;
import com.example.framework.mvvm.BR;
import com.example.framework.mvvm.R;
import com.example.framework.mvvm.databinding.FragmentBlogBinding;
import com.example.framework.mvvm.di.component.FragmentComponent;
import com.example.framework.mvvm.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by amitshekhar on 10/07/17.
 */

public class BlogFragment extends BaseFragment<FragmentBlogBinding, BlogViewModel>
        implements BlogNavigator, BlogAdapter.BlogAdapterListener {

    @Inject
    BlogAdapter mBlogAdapter;

    FragmentBlogBinding mFragmentBlogBinding;

    @Inject
    LinearLayoutManager mLayoutManager;

    public static BlogFragment newInstance() {
        Bundle args = new Bundle();
        BlogFragment fragment = new BlogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_blog;
    }


    @Override
    public void handleError(Throwable throwable) {
        // handle error
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.setNavigator(this);
        mBlogAdapter.setListener(this);
    }

    @Override
    public void onRetryClick() {
        mViewModel.fetchBlogs();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentBlogBinding = getViewDataBinding();
        setUp();
    }

    @Override
    public void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    public void updateBlog(List<BlogResponse.Blog> blogList) {
        mBlogAdapter.addItems(blogList);
    }

    private void setUp() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentBlogBinding.blogRecyclerView.setLayoutManager(mLayoutManager);
        mFragmentBlogBinding.blogRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mFragmentBlogBinding.blogRecyclerView.setAdapter(mBlogAdapter);
    }
}

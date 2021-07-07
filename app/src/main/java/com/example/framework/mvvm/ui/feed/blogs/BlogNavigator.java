package com.example.framework.mvvm.ui.feed.blogs;

import com.example.framework.mvvm.data.model.api.BlogResponse;

import java.util.List;

public interface BlogNavigator {

    void handleError(Throwable throwable);

    void updateBlog(List<BlogResponse.Blog> blogList);
}

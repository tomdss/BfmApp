package com.example.framework.mvvm.ui.home.news;

import com.example.framework.mvvm.data.model.api.NewsItemResponse;

import java.util.List;

public interface NewsNavigator {

    void handleError(Throwable throwable);

    void updateNewsList(List<NewsItemResponse> list);

}

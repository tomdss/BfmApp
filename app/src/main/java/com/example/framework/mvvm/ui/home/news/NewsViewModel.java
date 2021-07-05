package com.example.framework.mvvm.ui.home.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.framework.mvvm.data.DataManager;
import com.example.framework.mvvm.data.model.api.NewsItemResponse;
import com.example.framework.mvvm.ui.base.BaseViewModel;
import com.example.framework.mvvm.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends BaseViewModel<NewsNavigator> {

    private final MutableLiveData<List<NewsItemResponse>> newsListLiveData;

    public NewsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        newsListLiveData = new MutableLiveData<>();
        getDataNews();
    }

    public void getDataNews() {
        setIsLoading(true);
        // fake data
        List<NewsItemResponse> itemResponseList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            NewsItemResponse newsItemResponse = new NewsItemResponse();
            newsItemResponse.setIdUser(String.valueOf(System.currentTimeMillis()));
            newsItemResponse.setName("Name "+i);
            newsItemResponse.setUrlAvatar("https://www.w3schools.com/w3css/img_avatar2.png");
            newsItemResponse.setUrlImage("https://images.pexels.com/photos/3225517/pexels-photo-3225517.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
            newsItemResponse.setLike(false);
            newsItemResponse.setCountLike(0);
            newsItemResponse.setDate(String.valueOf(System.currentTimeMillis()));
            newsItemResponse.setContent("looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum");
            itemResponseList.add(newsItemResponse);
        }
        newsListLiveData.setValue(itemResponseList);
        setIsLoading(false);
    }

    public LiveData<List<NewsItemResponse>> getNewsListLiveData(){
        return newsListLiveData;
    }
}

package com.example.framework.mvvm.ui.feed.blogs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.framework.mvvm.data.DataManager;
import com.example.framework.mvvm.data.model.api.BlogResponse;
import com.example.framework.mvvm.utils.rx.SchedulerProvider;
import com.example.framework.mvvm.ui.base.BaseViewModel;

import java.util.List;

public class BlogViewModel extends BaseViewModel<BlogNavigator> {

    private final MutableLiveData<List<BlogResponse.Blog>> blogListLiveData;

    public BlogViewModel(DataManager dataManager,
                         SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        blogListLiveData = new MutableLiveData<>();
        fetchBlogs();
    }

    public void fetchBlogs() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getBlogApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(blogResponse -> {
                    if (blogResponse != null && blogResponse.getData() != null) {
                        blogListLiveData.setValue(blogResponse.getData());
                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public LiveData<List<BlogResponse.Blog>> getBlogListLiveData() {
        return blogListLiveData;
    }
}

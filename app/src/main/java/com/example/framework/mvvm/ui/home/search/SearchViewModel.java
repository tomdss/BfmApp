package com.example.framework.mvvm.ui.home.search;

import com.example.framework.mvvm.data.DataManager;
import com.example.framework.mvvm.ui.base.BaseViewModel;
import com.example.framework.mvvm.utils.rx.SchedulerProvider;

public class SearchViewModel extends BaseViewModel<SearchNavigator> {
    public SearchViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}

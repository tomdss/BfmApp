package com.example.framework.mvvm.ui.home;

import com.example.framework.mvvm.data.DataManager;
import com.example.framework.mvvm.ui.base.BaseViewModel;
import com.example.framework.mvvm.utils.rx.SchedulerProvider;

public class HomeViewModel extends BaseViewModel<HomeNavigator> {


    public HomeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

}

package com.example.framework.mvvm.ui.home.profile;

import com.example.framework.mvvm.data.DataManager;
import com.example.framework.mvvm.ui.base.BaseViewModel;
import com.example.framework.mvvm.utils.rx.SchedulerProvider;

public class ProfileViewModel extends BaseViewModel<ProfileNavigator> {
    public ProfileViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}

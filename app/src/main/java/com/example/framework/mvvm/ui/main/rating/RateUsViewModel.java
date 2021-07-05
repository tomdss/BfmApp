package com.example.framework.mvvm.ui.main.rating;

import com.example.framework.mvvm.data.DataManager;
import com.example.framework.mvvm.utils.rx.SchedulerProvider;
import com.example.framework.mvvm.ui.base.BaseViewModel;

public class RateUsViewModel extends BaseViewModel<RateUsCallback> {

    public RateUsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onLaterClick() {
        getNavigator().dismissDialog();
    }

    public void onSubmitClick() {
        getNavigator().dismissDialog();
    }
}

package com.example.framework.mvvm.ui.home.listgame;

import com.example.framework.mvvm.data.DataManager;
import com.example.framework.mvvm.ui.base.BaseViewModel;
import com.example.framework.mvvm.utils.rx.SchedulerProvider;

public class ListGameViewModel extends BaseViewModel<ListGameNavigator> {

    public ListGameViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onBillionClick() {
        getNavigator().onBillionClick();
    }

    public void onNumberClick() {
        getNavigator().onNumberClick();
    }
}

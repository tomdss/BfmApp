package com.example.framework.mvvm.ui.feed;

import com.example.framework.mvvm.data.DataManager;
import com.example.framework.mvvm.utils.rx.SchedulerProvider;
import com.example.framework.mvvm.ui.base.BaseViewModel;

/**
 * Created by Jyoti on 29/07/17.
 */

public class FeedViewModel extends BaseViewModel {

    public FeedViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}

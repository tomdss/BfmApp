

package com.example.framework.mvvm.ui.about;

import com.example.framework.mvvm.data.DataManager;
import com.example.framework.mvvm.utils.rx.SchedulerProvider;
import com.example.framework.mvvm.ui.base.BaseViewModel;



public class AboutViewModel extends BaseViewModel<AboutNavigator> {

    public AboutViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNavBackClick() {
        getNavigator().goBack();
    }
}

package com.example.framework.mvvm.di.module;

import androidx.core.util.Supplier;
import androidx.lifecycle.ViewModelProvider;

import com.example.framework.mvvm.data.DataManager;
import com.example.framework.mvvm.ui.base.BaseDialog;
import com.example.framework.mvvm.ui.main.rating.RateUsViewModel;
import com.example.framework.mvvm.utils.rx.SchedulerProvider;
import com.example.framework.mvvm.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class DialogModule {

    private BaseDialog dialog;

    public DialogModule(BaseDialog dialog){
        this.dialog = dialog;
    }

    @Provides
    RateUsViewModel provideRateUsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<RateUsViewModel> supplier = () -> new RateUsViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<RateUsViewModel> factory = new ViewModelProviderFactory<>(RateUsViewModel.class, supplier);
        return new ViewModelProvider(dialog.getActivity(), factory).get(RateUsViewModel.class);
    }

}

package com.example.framework.mvvm.di.component;

import android.app.Application;

import com.example.framework.mvvm.data.DataManager;
import com.example.framework.mvvm.utils.rx.SchedulerProvider;
import com.example.framework.mvvm.MvvmApp;

import com.example.framework.mvvm.di.module.AppModule;

import dagger.BindsInstance;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(MvvmApp app);

    DataManager getDataManager();

    SchedulerProvider getSchedulerProvider();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}

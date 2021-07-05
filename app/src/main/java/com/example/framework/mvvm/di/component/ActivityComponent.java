package com.example.framework.mvvm.di.component;

import com.example.framework.mvvm.ui.feed.FeedActivity;
import com.example.framework.mvvm.ui.home.HomeActivity;
import com.example.framework.mvvm.ui.login.LoginActivity;
import com.example.framework.mvvm.ui.main.MainActivity;
import com.example.framework.mvvm.ui.splash.SplashActivity;
import com.example.framework.mvvm.di.module.ActivityModule;
import com.example.framework.mvvm.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {

    void inject(FeedActivity activity);

    void inject(LoginActivity activity);

    void inject(MainActivity activity);

    void inject(SplashActivity splashActivity);

    void inject(HomeActivity homeActivity);
}

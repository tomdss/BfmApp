package com.example.framework.mvvm.di.component;

import com.example.framework.mvvm.ui.about.AboutFragment;
import com.example.framework.mvvm.ui.feed.blogs.BlogFragment;
import com.example.framework.mvvm.ui.feed.opensource.OpenSourceFragment;
import com.example.framework.mvvm.di.module.FragmentModule;
import com.example.framework.mvvm.di.scope.FragmentScope;
import com.example.framework.mvvm.ui.home.listgame.ListGameFragment;
import com.example.framework.mvvm.ui.home.news.NewsFragment;
import com.example.framework.mvvm.ui.home.profile.ProfileFragment;
import com.example.framework.mvvm.ui.home.search.SearchFragment;

import dagger.Component;

@FragmentScope
@Component(modules = FragmentModule.class, dependencies = AppComponent.class)
public interface FragmentComponent {
    void inject(BlogFragment fragment);

    void inject(OpenSourceFragment fragment);

    void inject(AboutFragment fragment);

    void inject(NewsFragment fragment);

    void inject(ProfileFragment fragment);

    void inject(SearchFragment fragment);

    void inject(ListGameFragment fragment);
}

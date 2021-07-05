package com.example.framework.mvvm.di.module;

import androidx.core.util.Supplier;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.framework.mvvm.data.DataManager;
import com.example.framework.mvvm.ui.about.AboutViewModel;
import com.example.framework.mvvm.ui.base.BaseFragment;
import com.example.framework.mvvm.ui.feed.blogs.BlogAdapter;
import com.example.framework.mvvm.ui.feed.blogs.BlogViewModel;
import com.example.framework.mvvm.ui.feed.opensource.OpenSourceAdapter;
import com.example.framework.mvvm.ui.feed.opensource.OpenSourceViewModel;
import com.example.framework.mvvm.ui.home.news.NewsAdapter;
import com.example.framework.mvvm.ui.home.news.NewsViewModel;
import com.example.framework.mvvm.ui.home.profile.ProfileViewModel;
import com.example.framework.mvvm.ui.home.search.SearchViewModel;
import com.example.framework.mvvm.utils.rx.SchedulerProvider;
import com.example.framework.mvvm.ViewModelProviderFactory;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    private BaseFragment<?, ?> fragment;

    public FragmentModule(BaseFragment<?, ?> fragment) {
        this.fragment = fragment;
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager() {
        return new LinearLayoutManager(fragment.getActivity());
    }

    @Provides
    AboutViewModel provideAboutViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<AboutViewModel> supplier = () -> new AboutViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<AboutViewModel> factory = new ViewModelProviderFactory<>(AboutViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(AboutViewModel.class);
    }

    @Provides
    BlogAdapter provideBlogAdapter() {
        return new BlogAdapter(new ArrayList<>());
    }

    @Provides
    NewsAdapter provideNewsAdapter(){
        return new NewsAdapter(new ArrayList<>());
    }


    @Provides
    BlogViewModel provideBlogViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<BlogViewModel> supplier = () -> new BlogViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<BlogViewModel> factory = new ViewModelProviderFactory<>(BlogViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(BlogViewModel.class);
    }

    @Provides
    OpenSourceAdapter provideOpenSourceAdapter() {
        return new OpenSourceAdapter();
    }

    @Provides
    OpenSourceViewModel provideOpenSourceViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<OpenSourceViewModel> supplier = () -> new OpenSourceViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<OpenSourceViewModel> factory = new ViewModelProviderFactory<>(OpenSourceViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(OpenSourceViewModel.class);
    }

    @Provides
    NewsViewModel provideNewsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<NewsViewModel> supplier = () -> new NewsViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<NewsViewModel> factory = new ViewModelProviderFactory<>(NewsViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(NewsViewModel.class);
    }

    @Provides
    SearchViewModel provideSearchViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<SearchViewModel> supplier = () -> new SearchViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<SearchViewModel> factory = new ViewModelProviderFactory<>(SearchViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(SearchViewModel.class);
    }

    @Provides
    ProfileViewModel provideProfileViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<ProfileViewModel> supplier = () -> new ProfileViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<ProfileViewModel> factory = new ViewModelProviderFactory<>(ProfileViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(ProfileViewModel.class);
    }
}

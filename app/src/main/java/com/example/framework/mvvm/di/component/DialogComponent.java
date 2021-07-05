package com.example.framework.mvvm.di.component;

import com.example.framework.mvvm.ui.main.rating.RateUsDialog;
import com.example.framework.mvvm.di.module.DialogModule;
import com.example.framework.mvvm.di.scope.DialogScope;

import dagger.Component;

@DialogScope
@Component(modules = DialogModule.class, dependencies = AppComponent.class)
public interface DialogComponent {

    void inject(RateUsDialog dialog);

}

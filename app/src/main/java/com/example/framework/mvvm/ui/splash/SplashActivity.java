package com.example.framework.mvvm.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import com.example.framework.mvvm.BR;
import com.example.framework.mvvm.R;
import com.example.framework.mvvm.databinding.ActivitySplashBinding;
import com.example.framework.mvvm.di.component.ActivityComponent;
import com.example.framework.mvvm.ui.base.BaseActivity;
import com.example.framework.mvvm.ui.login.LoginActivity;
import com.example.framework.mvvm.ui.main.MainActivity;

public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> implements SplashNavigator {

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.newIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.newIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.setNavigator(this);
        mViewModel.startSeeding();
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }
}



package com.example.framework.mvvm.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.framework.mvvm.BR;
import com.example.framework.mvvm.R;
import com.example.framework.mvvm.databinding.ActivityLoginBinding;
import com.example.framework.mvvm.di.component.ActivityComponent;
import com.example.framework.mvvm.ui.base.BaseActivity;
import com.example.framework.mvvm.ui.main.MainActivity;



public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements LoginNavigator {

    private ActivityLoginBinding mActivityLoginBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void handleError(Throwable throwable) {
        // handle error
    }

    @Override
    public void login() {
        String email = mActivityLoginBinding.etEmail.getText().toString();
        String password = mActivityLoginBinding.etPassword.getText().toString();
        if (mViewModel.isEmailAndPasswordValid(email, password)) {
            hideKeyboard();
            mViewModel.login(email, password);
        } else {
            Toast.makeText(this, getString(R.string.invalid_email_password), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.newIntent(LoginActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityLoginBinding = getViewDataBinding();
        mViewModel.setNavigator(this);
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }
}

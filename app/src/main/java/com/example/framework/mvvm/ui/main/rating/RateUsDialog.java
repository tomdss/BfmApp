package com.example.framework.mvvm.ui.main.rating;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.framework.mvvm.di.component.DaggerDialogComponent;
import com.example.framework.mvvm.MvvmApp;
import com.example.framework.mvvm.R;
import com.example.framework.mvvm.databinding.DialogRateUsBinding;
import com.example.framework.mvvm.di.component.DialogComponent;
import com.example.framework.mvvm.di.module.DialogModule;
import com.example.framework.mvvm.ui.base.BaseDialog;
import javax.inject.Inject;

public class RateUsDialog extends BaseDialog implements RateUsCallback {

    private static final String TAG = "RateUsDialog";

    @Inject
    RateUsViewModel mRateUsViewModel;

    public static RateUsDialog newInstance() {
        RateUsDialog fragment = new RateUsDialog();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void dismissDialog() {
        dismissDialog(TAG);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DialogRateUsBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_rate_us, container, false);
        View view = binding.getRoot();

        performDependencyInjection(getBuildComponent());

        binding.setViewModel(mRateUsViewModel);

        mRateUsViewModel.setNavigator(this);

        return view;
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, TAG);
    }

    private DialogComponent getBuildComponent(){
        return DaggerDialogComponent.builder()
                .appComponent(((MvvmApp)(getContext().getApplicationContext())).appComponent)
                .dialogModule(new DialogModule(this))
                .build();
    }

    private void performDependencyInjection(DialogComponent buildComponent){
        buildComponent.inject(this);
    }
}

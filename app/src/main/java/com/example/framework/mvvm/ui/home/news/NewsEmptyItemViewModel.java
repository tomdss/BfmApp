package com.example.framework.mvvm.ui.home.news;

public class NewsEmptyItemViewModel {

    private NewsEmptyItemViewModelListener mListener;

    public NewsEmptyItemViewModel(NewsEmptyItemViewModelListener listener) {
        this.mListener = listener;
    }

    public void onRetryClick() {
        mListener.onRetryClick();
    }

    public interface NewsEmptyItemViewModelListener {
        void onRetryClick();
    }
}

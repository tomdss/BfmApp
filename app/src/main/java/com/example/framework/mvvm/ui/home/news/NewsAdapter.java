package com.example.framework.mvvm.ui.home.news;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.mvvm.data.model.api.NewsItemResponse;
import com.example.framework.mvvm.databinding.ItemNewsBinding;
import com.example.framework.mvvm.databinding.ItemNewsEmptyViewBinding;
import com.example.framework.mvvm.ui.base.BaseViewHolder;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final String TAG = NewsAdapter.class.getSimpleName();

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private List<NewsItemResponse> mNewsList;

    private NewsAdapterListener mListener;

    public NewsAdapter(List<NewsItemResponse> mNewsList) {
        this.mNewsList = mNewsList;
    }

    public void setListener(NewsAdapterListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                ItemNewsBinding itemNewsBinding = ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new NewsViewHolder(itemNewsBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemNewsEmptyViewBinding itemNewsEmptyViewBinding = ItemNewsEmptyViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new EmptyViewHolder(itemNewsEmptyViewBinding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    public void addItems(List<NewsItemResponse> newsItemResponseList) {
        mNewsList.addAll(newsItemResponseList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mNewsList.clear();
    }

    @Override
    public int getItemCount() {
        if (mNewsList != null && mNewsList.size() > 0) {
            return mNewsList.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mNewsList != null && !mNewsList.isEmpty()) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    public interface NewsAdapterListener {
        void onRetryClick();
    }

    public class NewsViewHolder extends BaseViewHolder implements NewsItemViewModel.NewsItemViewModelListener {

        private ItemNewsBinding mBinding;

        private NewsItemViewModel mNewsItemViewModel;

        public NewsViewHolder(ItemNewsBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }


        @Override
        public void onBind(int position) {
            final NewsItemResponse itemResponse = mNewsList.get(position);
            mNewsItemViewModel = new NewsItemViewModel(itemResponse, this);
            mBinding.setViewModel(mNewsItemViewModel);
            mBinding.executePendingBindings();
        }

        @Override
        public void onItemClick(NewsItemResponse newsItemResponse) {
            Log.d(TAG, "onItemClick");
        }

        @Override
        public void onAvatarClick(String idUser) {
            Log.d(TAG, "onAvatarClick");
        }

        @Override
        public void onNameClick(String idUser) {
            Log.d(TAG, "onNameClick");
        }

        @Override
        public void onLikeClick(String idUser) {
            Log.d(TAG, "onLikeClick");
        }

        @Override
        public void onImageClick(String idUser) {
            Log.d(TAG, "onImageClick");
        }
    }

    public class EmptyViewHolder extends BaseViewHolder implements NewsEmptyItemViewModel.NewsEmptyItemViewModelListener {

        private ItemNewsEmptyViewBinding mBinding;

        public EmptyViewHolder(ItemNewsEmptyViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            NewsEmptyItemViewModel newsEmptyItemViewModel = new NewsEmptyItemViewModel(this);
            mBinding.setViewModel(newsEmptyItemViewModel);
        }

        @Override
        public void onRetryClick() {
            mListener.onRetryClick();
            Log.d(TAG, "onRetryClick");
        }
    }

}

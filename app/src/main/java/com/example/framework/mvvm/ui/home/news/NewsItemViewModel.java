package com.example.framework.mvvm.ui.home.news;

import androidx.databinding.ObservableField;

import com.example.framework.mvvm.data.model.api.NewsItemResponse;

public class NewsItemViewModel {

    public final ObservableField<String> urlAvatar;
    public final ObservableField<String> name;
    public final ObservableField<String> id;
    public final ObservableField<String> idUser;
    public final ObservableField<String> date;
    public final ObservableField<String> content;
    public final ObservableField<Boolean> isLike;
    public final ObservableField<Integer> countLike;
    public final ObservableField<String> urlImage;

    public final NewsItemViewModelListener mListener;

    private final NewsItemResponse mNewsItem;

    public NewsItemViewModel(NewsItemResponse itemResponse, NewsItemViewModelListener listener) {
        this.mNewsItem = itemResponse;
        this.mListener = listener;

        this.urlAvatar = new ObservableField<>(mNewsItem.getUrlAvatar());
        this.name = new ObservableField<>(mNewsItem.getName());
        this.id = new ObservableField<>(mNewsItem.getId());
        this.idUser = new ObservableField<>(mNewsItem.getIdUser());
        this.date = new ObservableField<>(mNewsItem.getDate());
        this.content = new ObservableField<>(mNewsItem.getContent());
        this.isLike = new ObservableField<>(mNewsItem.getLike());
        this.countLike = new ObservableField<>(mNewsItem.getCountLike());
        this.urlImage = new ObservableField<>(mNewsItem.getUrlImage());
    }

    public interface NewsItemViewModelListener {
        void onItemClick(NewsItemResponse newsItemResponse);

        void onAvatarClick(String idUser);

        void onNameClick(String idUser);

        void onLikeClick(String idUser);

        void onImageClick(String idUser);
    }
}

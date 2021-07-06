package com.example.framework.mvvm.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Pair;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;

import com.example.framework.mvvm.R;
import com.example.framework.mvvm.databinding.LayoutAppBarCollapsProfileBinding;
import com.google.android.material.appbar.AppBarLayout;

public class AppBarCollapsingAvatar extends AppBarLayout {
    
    private LayoutAppBarCollapsProfileBinding mBinding;
    private Context mContext;

    public static final Float SWITCH_BOUND = 0.8f;
    public static final int TO_EXPANDED = 0;
    public static final int TO_COLLAPSED = 1;
    public static final int WAIT_FOR_SWITCH = 0;
    public static final int SWITCHED = 1;

    private Boolean mIsCalculated = false;
    private Float EXPAND_AVATAR_SIZE = 0F;
    private Float COLLAPSE_IMAGE_SIZE = 0F;
    private Float horizontalToolbarAvatarMargin = 0F;
    private Float avatarAnimateStartPointY = 0F;
    private Float avatarCollapseAnimationChangeWeight = 0F;
    private Float verticalToolbarAvatarMargin = 0F;
    private Pair<Integer, Integer> cashCollapseState = null;


    public AppBarCollapsingAvatar(Context context) {
        super(context);
        setUp(context);
    }

    public AppBarCollapsingAvatar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUp(context);
    }

    private void setUp(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mBinding = LayoutAppBarCollapsProfileBinding.inflate(inflater);
        this.mContext = context;
        EXPAND_AVATAR_SIZE = getResources().getDimension(R.dimen.default_expanded_image_size);
        COLLAPSE_IMAGE_SIZE = getResources().getDimension(R.dimen.default_collapsed_image_size);
        horizontalToolbarAvatarMargin = getResources().getDimension(R.dimen.activity_margin);
        updateProfileView();
    }

    private void updateProfileView() {
        mBinding.appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if (!mIsCalculated) {
                    avatarAnimateStartPointY = Math.abs((mBinding.appBarLayout.getHeight() - (EXPAND_AVATAR_SIZE + horizontalToolbarAvatarMargin)) / mBinding.appBarLayout.getTotalScrollRange());
                    avatarCollapseAnimationChangeWeight = 1 / (1 - avatarAnimateStartPointY);
                    verticalToolbarAvatarMargin = (mBinding.animToolbar.getHeight() - COLLAPSE_IMAGE_SIZE) * 2;
                    mIsCalculated = true;
                }
                updateViews((float) Math.abs(i / mBinding.appBarLayout.getTotalScrollRange()));
            }
        });
    }

    private void updateViews(Float offset) {
        if (offset >= 0.15F && offset <= 1F) {
            if (mBinding.tvProfileName.getVisibility() != View.VISIBLE) {
                mBinding.tvProfileName.setVisibility(View.VISIBLE);
            }
            mBinding.tvProfileName.setAlpha((1 - offset) * 0.35F);
        } else if (offset >= 0F && offset <= 0.15F) {
            mBinding.tvProfileName.setAlpha(1F);
            mBinding.imgbAvatarWrap.setAlpha(1F);
        }

        /** collapse - expand switch*/
        Pair<Integer, Integer> integerPair;
        if (offset < SWITCH_BOUND) {
            if (cashCollapseState != null && cashCollapseState.second != null) {
                integerPair = new Pair(TO_EXPANDED, cashCollapseState.second);
            } else {
                integerPair = new Pair(TO_EXPANDED, WAIT_FOR_SWITCH);
            }
        } else {
            if (cashCollapseState != null && cashCollapseState.second != null) {
                integerPair = new Pair(TO_COLLAPSED, cashCollapseState.second);
            } else {
                integerPair = new Pair(TO_COLLAPSED, WAIT_FOR_SWITCH);
            }
        }
        if (cashCollapseState != null && cashCollapseState != integerPair) {
            if (integerPair.first == TO_EXPANDED) {
                mBinding.imgbAvatarWrap.setTranslationX(0F);
                mBinding.flBackground.setBackgroundColor(ContextCompat.getColor(mContext, R.color.transparent));
                mBinding.tvProfileNameSingle.setVisibility(View.INVISIBLE);
            } else if (integerPair.first == TO_COLLAPSED) {
                mBinding.flBackground.setAlpha(0F);
                mBinding.flBackground.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
                mBinding.flBackground.animate().setDuration(250).alpha(1.0F);
                /* show titles on toolbar with animation*/
                mBinding.tvProfileNameSingle.setVisibility(View.VISIBLE);
                mBinding.tvProfileNameSingle.setAlpha(0F);
                mBinding.tvProfileNameSingle.animate().setDuration(500).alpha(1.0F);
            }
            cashCollapseState = new Pair(integerPair.first, SWITCHED);
        } else {
            cashCollapseState = new Pair(integerPair.first, WAIT_FOR_SWITCH);
        }


        /* Collapse avatar img*/
        if (offset > avatarAnimateStartPointY) {
            float avatarCollapseAnimateOffset = (offset - avatarAnimateStartPointY) * avatarCollapseAnimationChangeWeight;
            float avatarSize = EXPAND_AVATAR_SIZE - (EXPAND_AVATAR_SIZE - COLLAPSE_IMAGE_SIZE) * avatarCollapseAnimateOffset;
            ViewGroup.LayoutParams layoutParams = mBinding.imgbAvatarWrap.getLayoutParams();
            layoutParams.height = Math.round(avatarSize);
            layoutParams.width = Math.round(avatarSize);
            mBinding.imgbAvatarWrap.requestLayout();
            mBinding.tvWorkaround.setTextSize(TypedValue.COMPLEX_UNIT_PX, offset);

            mBinding.imgbAvatarWrap.setTranslationX(((mBinding.appBarLayout.getWidth() - horizontalToolbarAvatarMargin - avatarSize) / 2) * avatarCollapseAnimateOffset);
            mBinding.imgbAvatarWrap.setTranslationY(((mBinding.animToolbar.getHeight() - verticalToolbarAvatarMargin - avatarSize) / 2) * avatarCollapseAnimateOffset);
        } else {
            if (mBinding.imgbAvatarWrap.getHeight() != Math.round(EXPAND_AVATAR_SIZE)) {
                ViewGroup.LayoutParams layoutParams = mBinding.imgbAvatarWrap.getLayoutParams();
                layoutParams.height = Math.round(Math.round(EXPAND_AVATAR_SIZE));
                layoutParams.width = Math.round(Math.round(EXPAND_AVATAR_SIZE));
                mBinding.imgbAvatarWrap.requestLayout();
            }
            mBinding.imgbAvatarWrap.setTranslationX(0F);
        }
    }

}

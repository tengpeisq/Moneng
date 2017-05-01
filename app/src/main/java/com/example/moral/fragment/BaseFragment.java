package com.example.moral.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by imxiaopeng on 2017/4/30.
 */

public abstract class BaseFragment extends Fragment {
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected ViewGroup mContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mInflater = inflater;
        this.mContainer = container;
        mContext = getActivity();
        View contentView = setContentView();
        ButterKnife.bind(this,contentView);
        initView();
        return contentView;
    }
    protected abstract void initView();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    protected abstract View setContentView() ;
    protected abstract void initData() ;
}

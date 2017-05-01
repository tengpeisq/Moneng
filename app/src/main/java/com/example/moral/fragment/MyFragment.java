package com.example.moral.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moral.R;

/**
 * Created by imxiaopeng on 2017/4/30.
 */

public class MyFragment extends BaseFragment {

    @Override
    protected void initView() {

    }

    @Override
    protected View setContentView() {
        View contentView = mInflater.inflate(R.layout.fragment_my,mContainer,false);
        return contentView;
    }

    @Override
    protected void initData() {

    }
}

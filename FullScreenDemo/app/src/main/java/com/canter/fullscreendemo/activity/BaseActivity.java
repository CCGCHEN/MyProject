package com.canter.fullscreendemo.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.canter.fullscreendemo.view.CommonFrameLayout;

/**
 * Description : BaseActivity.java
 * Creation    : 2020-04-11
 * Author      : cangui.ccg
 */
public abstract class BaseActivity extends Activity {

    protected CommonFrameLayout mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContentView = new CommonFrameLayout(this);
        setContentView(mContentView);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mContentView.addView(getContainer(), layoutParams);
    }

    protected Context getContext() {
        return this;
    }

    protected abstract View getContainer();

    protected String getTag() {
        return getClass().getSimpleName();
    }

    protected void logI(String msg) {
        Log.i(getTag(), msg);
    }

}

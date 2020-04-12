package com.canter.fullscreendemo.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.canter.fullscreendemo.R;
import com.canter.fullscreendemo.utils.Util;

/**
 * Description : TransprantBarActivity.java
 * Creation    : 2020-04-12
 * Author      : cangui.ccg
 */
public class TransprantBarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //第一种方式
//        mContentView.setFitsSystemWindows(true);

        //第二种方式
        mContentView.setPadding(0, Util.getStatusBarHeight(getContext()), 0, 0);
    }

    @Override
    protected View getContainer() {
        FrameLayout frameLayout = new FrameLayout(getContext());
        ImageView imageView = new ImageView(getContext());
        Drawable drawable = getResources().getDrawable(R.drawable.bg);
        imageView.setImageDrawable(drawable);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500);
        layoutParams.gravity = Gravity.TOP;
        frameLayout.addView(imageView, layoutParams);
        return frameLayout;
    }
}

package com.canter.fullscreendemo.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.canter.fullscreendemo.R;

/**
 * Description : VideoActivity.java
 * Creation    : 2020-04-05
 * Author      : cangui.ccg
 */
public class FullScreenActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏模式
        int flag = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        mContentView.setSystemUiVisibility(flag);
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

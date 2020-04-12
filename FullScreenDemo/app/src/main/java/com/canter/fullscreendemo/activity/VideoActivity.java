package com.canter.fullscreendemo.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.canter.fullscreendemo.R;
import com.canter.fullscreendemo.view.Text;
import com.canter.fullscreendemo.view.ToolLayer;

/**
 * Description : VideoActivity.java
 * Creation    : 2020-04-05
 * Author      : cangui.ccg
 */
public class VideoActivity extends BaseActivity {

    private boolean isFullScreen;
    private TextView mButton;
    private ToolLayer mToolLayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected View getContainer() {
        //进入全屏
        enterFullScreen();

        FrameLayout frameLayout = new FrameLayout(getContext());

        //顶部icon
        ImageView topIv = new ImageView(getContext());
        Drawable topDrawable = getResources().getDrawable(R.drawable.bg);
        topIv.setImageDrawable(topDrawable);
        topIv.setScaleType(ImageView.ScaleType.FIT_XY);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500);
        layoutParams.gravity = Gravity.TOP;
        frameLayout.addView(topIv, layoutParams);


        //底部icon
        ImageView bottomIv = new ImageView(getContext());
        Drawable bottomDrawable = getResources().getDrawable(R.drawable.bg);
        bottomIv.setImageDrawable(bottomDrawable);
        bottomIv.setScaleType(ImageView.ScaleType.FIT_XY);
        layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500);
        layoutParams.gravity = Gravity.BOTTOM;
        frameLayout.addView(bottomIv, layoutParams);

        //中间按钮
        mButton = Text.create(getContext()).textGravity(Gravity.CENTER).build();
        mButton.setBackgroundColor(Color.CYAN);

        layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
        layoutParams.gravity = Gravity.CENTER;
        frameLayout.addView(mButton, layoutParams);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFullScreen) {
                    hideFullScreen();
                } else {
                    enterFullScreen();
                }
                configUI();
            }
        });

        mToolLayer = new ToolLayer(getContext());
        mToolLayer.setBackgroundColor(Color.YELLOW);
        mToolLayer.setAlpha(0.5f);
        frameLayout.addView(mToolLayer, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        configUI();
        return frameLayout;
    }

    private void configUI() {
        mButton.setText(isFullScreen ? "退出全屏" : "进入全屏");
        mToolLayer.setVisibility(isFullScreen ? View.INVISIBLE : View.VISIBLE);
    }

    private void enterFullScreen() {
        isFullScreen = true;
        int flag = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        mContentView.setSystemUiVisibility(flag);
    }

    private void hideFullScreen() {
        isFullScreen = false;
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
}

package com.canter.fullscreendemo.view;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

/**
 * Description : ToolLayer.java
 * Creation    : 2020-04-12
 * Author      : cangui.ccg
 */
public class ToolLayer extends FrameLayout {

    private TextView mTopBar;
    private TextView mBottomBar;

    public ToolLayer(@NonNull Context context) {
        super(context);

        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
        mTopBar = Text.create(getContext()).text("顶部状态栏").textGravity(Gravity.TOP).textColor(Color.WHITE).textSize(150).build();
        mTopBar.setBackgroundColor(Color.BLUE);
        layoutParams.gravity = Gravity.TOP;
        addView(mTopBar, layoutParams);

        mBottomBar = Text.create(getContext()).text("底部导航栏").textGravity(Gravity.BOTTOM).textSize(150).textColor(Color.WHITE).build();
        mBottomBar.setBackgroundColor(Color.BLUE);
        layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
        layoutParams.gravity = Gravity.BOTTOM;
        addView(mBottomBar, layoutParams);
    }
}

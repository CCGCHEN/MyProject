package com.canter.fullscreendemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.canter.fullscreendemo.activity.BaseActivity;
import com.canter.fullscreendemo.activity.FullScreenActivity;
import com.canter.fullscreendemo.activity.NovelActivity;
import com.canter.fullscreendemo.activity.TransprantBarActivity;
import com.canter.fullscreendemo.activity.VideoActivity;
import com.canter.fullscreendemo.data.ItemData;
import com.canter.fullscreendemo.utils.ContextManager;
import com.canter.fullscreendemo.view.Text;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final int ID_TRANSPRANTBAR = 101;
    private static final int ID_FULLSCREEN = 102;
    private static final int ID_VIDEO = 103;
    private static final int ID_NOVEL = 104;

    private static final String TAG = "MainActivity";

    @Override
    protected View getContainer() {
        ContextManager.setContext(getContext());
        ScrollView scrollView = new ScrollView(getContext());
        FrameLayout container = new FrameLayout(getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scrollView.addView(container, layoutParams);

        int itemWidth = ViewGroup.LayoutParams.MATCH_PARENT;
        int itemHeight = 100;
        int topMargin = 150;

        //initData
        List<ItemData> dataList = new ArrayList<>();
        dataList.add(new ItemData().setItemName("透明状态栏").setViewId(ID_TRANSPRANTBAR));
        dataList.add(new ItemData().setItemName("全屏模式").setViewId(ID_FULLSCREEN));
        dataList.add(new ItemData().setItemName("视频播放").setViewId(ID_VIDEO));
        dataList.add(new ItemData().setItemName("小说阅读").setViewId(ID_NOVEL));

        //initView
        for (int i = 0; i < dataList.size(); i++) {
            layoutParams = new FrameLayout.LayoutParams(itemWidth, itemHeight);
            layoutParams.topMargin = topMargin * i;
            container.addView(createItemView(dataList.get(i)), layoutParams);
        }
        return scrollView;
    }

    private ItemView createItemView(ItemData itemData) {
        ItemView itemView = new ItemView(getContext(), itemData);
        itemView.setOnClickListener(this);
        return itemView;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case ID_TRANSPRANTBAR:
                intent.setClass(getContext(), TransprantBarActivity.class);
                break;
            case ID_FULLSCREEN:
                intent.setClass(getContext(), FullScreenActivity.class);
                break;
            case ID_VIDEO:
                intent.setClass(getContext(), VideoActivity.class);
                break;
            case ID_NOVEL:
                intent.setClass(getContext(), NovelActivity.class);
                break;
            default:
                break;
        }
        startActivity(intent);
    }

    class ItemView extends FrameLayout {
        ItemData itemData;
        public ItemView(@NonNull Context context, ItemData itemData) {
            super(context);
            this.itemData = itemData;
            TextView textView = Text.create(getContext()).text(itemData.getItemName()).textGravity(Gravity.CENTER).bold().build();
            addView(textView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            setBackgroundColor(Color.CYAN);
            setId(itemData.getViewId());
        }
    }
}

package com.canter.fullscreendemo.utils;

import android.content.Context;

/**
 * Description : ContextManager.java
 * Creation    : 2020-04-03
 * Author      : cangui.ccg
 */
public class ContextManager {

    private static Context mContext;
    public static Context getContext() {
        return mContext;
    }

    public static void setContext(Context context) {
        mContext = context;
    }
}

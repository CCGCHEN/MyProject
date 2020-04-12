package com.canter.fullscreendemo.utils;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import java.lang.reflect.Field;

/**
 * Description : Util.java
 * Creation    : 2020-04-03
 * Author      : cangui.ccg
 */
public class Util {

    private static int sStatusBarHeight;
    private static boolean sHasCheckStatusBarHeight;

    public static int getStatusBarHeight(Context context) {
        if (sHasCheckStatusBarHeight) {
            return sStatusBarHeight;
        }
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object o = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = (Integer) field.get(o);
            sStatusBarHeight = context.getResources().getDimensionPixelSize(x);
            sHasCheckStatusBarHeight = true;
        } catch (Exception e) {
            sStatusBarHeight = guessStatusBarHeight(context);
            sHasCheckStatusBarHeight = true;
            ExceptionHandler.processFatalException(e);
        }
        return sStatusBarHeight;
    }

    private static int guessStatusBarHeight(Context context) {
        try {
            if (context != null) {
                final int statusBarHeightDP = 25;
                float density = context.getResources().getDisplayMetrics().density;
                return Math.round(density*statusBarHeightDP);
            }
        } catch (Exception e) {
            ExceptionHandler.processFatalException(e);
        }
        return 0;
    }

    public static int getSystemStatusBarHeight() {
        return getStatusBarHeight(ContextManager.getContext());
    }

    private static int sNaviBarHeight = -1;
    public static int getNaviBarHeight(Context context) {
        if (sNaviBarHeight > 0) {
            return sNaviBarHeight;
        }
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object o = c.newInstance();
            Field field = c.getField("navigation_bar_height");
            int x = (Integer) field.get(o);
            sNaviBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            final int dp = 48;
            float density = context.getResources().getDisplayMetrics().density;
            sNaviBarHeight = Math.round(density * dp);

        }
        return sNaviBarHeight;
    }

    public static Animation createTranslateYAnimation(float fromY, float toY, int duration) {
        TranslateAnimation anim = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0,
                Animation.RELATIVE_TO_SELF, fromY, Animation.RELATIVE_TO_SELF, toY);
        anim.setDuration(duration);
        return anim;
    }

}

package com.yunxian.immerse.impl;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.yunxian.immerse.IImmerseMode;
import com.yunxian.immerse.utils.WindowUtils;

import java.lang.ref.SoftReference;

import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
import static android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS;
import static android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
import static android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;

/**
 * 全透明状态栏模式下的实现
 * <p>仅支持5.0以上系统</p>
 *
 * @author AShuai
 * @email ls1110924@gmail.com
 * @date 17/1/24 下午5:28
 */
@TargetApi(LOLLIPOP)
public class TransparentImmerseMode implements IImmerseMode {

    private final SoftReference<Activity> mActivityRef;

    /**
     * 构造方法
     *
     * @param activity   待沉浸的Activity对象
     * @param fullScreen 是否全屏。true为全屏，用户内容需要延伸到状态栏之下；false为正常模式，用户内容需要布局在状态栏之外
     */
    public TransparentImmerseMode(@NonNull Activity activity, boolean fullScreen) {
        mActivityRef = new SoftReference<>(activity);

        Window window = activity.getWindow();
        WindowUtils.clearWindowFlags(window, FLAG_TRANSLUCENT_STATUS | FLAG_TRANSLUCENT_NAVIGATION);
        WindowUtils.addWindowFlags(window, FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        window.getDecorView().setSystemUiVisibility(SYSTEM_UI_FLAG_LAYOUT_STABLE | SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        if (fullScreen) {
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        setupContentView(activity);
    }

    @Override
    public void setStatusColor(@ColorInt int color) {
        Activity activity = mActivityRef.get();
        if (activity != null) {
            activity.getWindow().setStatusBarColor(color);
        }
    }

    @Override
    public void setStatusColorRes(@ColorRes int colorRes) {
        Activity activity = mActivityRef.get();
        if (activity != null) {
            setStatusColor(ContextCompat.getColor(activity, colorRes));
        }
    }

    @Override
    public boolean setStatusDrawable(@Nullable Drawable drawable) {
        return false;
    }

    @Override
    public boolean setStatusDrawableRes(@DrawableRes int drawableRes) {
        return false;
    }

    private void setupContentView(@NonNull Activity activity) {
        ViewGroup contentViewGroup = (ViewGroup) activity.findViewById(android.R.id.content);
        View userView = contentViewGroup.getChildAt(0);
        userView.setFitsSystemWindows(true);
    }
}

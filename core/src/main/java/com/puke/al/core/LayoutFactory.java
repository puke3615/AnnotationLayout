package com.puke.al.core;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author zijiao
 * @version 17/10/16
 */
public interface LayoutFactory {

    ViewGroup create(Context context);

    void addView(ViewGroup viewGroup, View view, ViewGroup.LayoutParams layoutParams);

    void addView(ViewGroup viewGroup, View view);

}

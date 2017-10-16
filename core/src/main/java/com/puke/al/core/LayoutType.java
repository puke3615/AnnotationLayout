package com.puke.al.core;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.lang.reflect.Constructor;

/**
 * @author zijiao
 * @version 17/10/16
 */
public enum LayoutType implements LayoutFactory {

    Linear(LinearLayout.class) {
        @Override
        public ViewGroup create(Context context) {
            LinearLayout layout = new LinearLayout(context);
            layout.setOrientation(LinearLayout.VERTICAL);
            return layout;
        }
    },
    Relative(RelativeLayout.class),;


    private final Class<? extends ViewGroup> layoutClass;

    LayoutType(Class<? extends ViewGroup> layoutClass) {
        this.layoutClass = layoutClass;
    }

    @Override
    public ViewGroup create(Context context) {
        try {
            Constructor<? extends ViewGroup> constructor = layoutClass.getConstructor(Context.class);
            return constructor.newInstance(context);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addView(ViewGroup viewGroup, View view, ViewGroup.LayoutParams layoutParams) {
        viewGroup.addView(view, layoutParams);
    }

    @Override
    public void addView(ViewGroup viewGroup, View view) {
        viewGroup.addView(view);
    }

}

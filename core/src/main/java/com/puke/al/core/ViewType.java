package com.puke.al.core;

import android.view.View;

/**
 * @author zijiao
 * @version 17/10/16
 */
public enum ViewType {

    Button(android.widget.Button.class),
    Label(android.widget.TextView.class),
    Image(android.widget.ImageView.class),
    Layout(android.widget.LinearLayout.class),;


    public final Class<? extends View> viewType;

    ViewType(Class<? extends View> viewType) {
        this.viewType = viewType;
    }


}

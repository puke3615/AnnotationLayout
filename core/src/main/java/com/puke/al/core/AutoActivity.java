package com.puke.al.core;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * @author zijiao
 * @version 17/10/16
 */
public class AutoActivity extends Activity {

    private IViewGenerator view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new AnnoViewGenerator(this);
    }

    protected void fillView(Object data) {
        if (view != null) {
            setContentView(view.generateView(data));
        }
    }


}

package com.puke.al.core;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * @author zijiao
 * @version 17/10/16
 */
public class UserInfoActivity extends AutoActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fillView(Mocks.mockUserInfo());
    }

}

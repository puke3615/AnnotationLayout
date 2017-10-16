package com.puke.al.core;

import java.util.ArrayList;

/**
 * @author zijiao
 * @version 17/10/16
 */
public class Mocks {

    public static UserInfo mockUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.name = "女装精选";
        userInfo.address = "杭州市西湖区";
        userInfo.age = 24;
        userInfo.goods = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            UserInfo.Goods goods = new UserInfo.Goods();
            goods.name = "休闲裤" + i;
            goods.desc = "百搭服饰";
            goods.price = 10.2f;
            userInfo.goods.add(goods);
        }
        return userInfo;
    }

}

package com.puke.al.core;

import com.puke.al.core.anno.Label;
import com.puke.al.core.anno.Layout;
import com.puke.al.core.anno.Order;

import java.util.List;

/**
 * @author zijiao
 * @version 17/10/16
 */
@Layout(LayoutType.Linear)
public class UserInfo {

    @Label("用户名: %s")
    public String name;
    @Label("地址: %s")
    public String address;
    @Order(-1)
    @Label("年龄: %d")
    public int age;
    @Layout(LayoutType.Linear)
    public List<Goods> goods;

    @Layout(LayoutType.Linear)
    public static class Goods {
        @Label("价格: %.2f")
        public float price = 3.1f;
        @Label("名称: %s")
        public String name = "呢大衣";
        @Label("描述: %s")
        public String desc = "秋冬换季百搭";
    }

}

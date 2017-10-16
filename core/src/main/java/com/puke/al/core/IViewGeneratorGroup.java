package com.puke.al.core;

/**
 * @author zijiao
 * @version 17/10/16
 */
public interface IViewGeneratorGroup extends IViewGenerator {

    IViewGenerator getChildAt(int index);

    int getChildCount();

}

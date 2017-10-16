package com.puke.al.core;

import android.content.Context;
import android.view.View;

import java.lang.annotation.Annotation;

/**
 * @author zijiao
 * @version 17/10/16
 */
public interface AnnotationParser {

    View createView(Context context, Object data, Annotation annotation) throws Exception;

}

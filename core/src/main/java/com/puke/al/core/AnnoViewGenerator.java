package com.puke.al.core;


import android.content.Context;
import android.view.View;

import com.puke.al.core.anno.Viewable;

import java.lang.annotation.Annotation;

/**
 * @author zijiao
 * @version 17/10/16
 */
public class AnnoViewGenerator implements IViewGenerator {

    private final Context context;

    public AnnoViewGenerator(Context context) {
        this.context = context;
    }

    @Override
    public View generateView(Object data) {
        if (data == null) {
            throw new RuntimeException("The data is null.");
        }
        try {
            return parseView(data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private View parseView(Object data) throws Exception {
        Class<?> cls = data.getClass();
        for (Annotation annotation : cls.getAnnotations()) {
            Class<? extends Annotation> annotationClass = annotation.annotationType();
            if (annotationClass.isAnnotationPresent(Viewable.class)) {
                AnnotationParser annotationParser = AnnotationParserManager.instance().getParser(annotationClass);
                return annotationParser.createView(context, data, annotation);
            }
        }
        return null;
    }


}

package com.puke.al.core.anno;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.puke.al.core.AnnotationParser;
import com.puke.al.core.AnnotationParserManager;
import com.puke.al.core.LayoutType;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author zijiao
 * @version 17/10/16
 */
@Inherited
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Viewable(Layout.LayoutParser.class)
public @interface Layout {

    LayoutType value() default LayoutType.Linear;

    class LayoutParser implements AnnotationParser {

        @Override
        public View createView(Context context, Object data, Annotation annotation) throws Exception {
            if (annotation instanceof Layout) {
                return newLayout(context, data, ((Layout) annotation));
            }
            return null;
        }

        private View parseView(Context context, Object data) {
            try {
                Class<?> cls = data.getClass();
                for (Annotation annotation : cls.getAnnotations()) {
                    if (canParse(annotation)) {
                        Class<? extends Annotation> annotationClass = annotation.annotationType();
                        AnnotationParser annotationParser = AnnotationParserManager.instance().getParser(annotationClass);
                        View view = annotationParser.createView(context, data, annotation);
                        if (view != null) {
                            return view;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        private boolean canParse(Annotation annotation) {
            return annotation.annotationType().isAnnotationPresent(Viewable.class);
        }


        private View newLayout(Context context, Object data, Layout layout) throws Exception {
            Class<?> cls = data.getClass();
            LayoutType layoutFactory = layout.value();
            ViewGroup viewGroup = layoutFactory.create(context);
            if (viewGroup == null) {
                return null;
            }
            List<ViewWrapper> childViews = new ArrayList<>();
            if (data instanceof List) {
                for (Object itemData : ((List) data)) {
                    View childView = parseView(context, itemData);
                    if (childView != null) {
                        childViews.add(new ViewWrapper(childView, 0));
                    }
                }
            } else {
                for (Field field : cls.getDeclaredFields()) {
                    field.setAccessible(true);
                    Object fieldValue = field.get(data);
                    View childView = null;
                    int order = field.isAnnotationPresent(Order.class) ? field.getAnnotation(Order.class).value() : 0;
                    Annotation[] annotations = field.getAnnotations();
                    for (Annotation annotation : annotations) {
                        if (canParse(annotation)) {
                            Class<? extends Annotation> annotationClass = annotation.annotationType();
                            AnnotationParser annotationParser = AnnotationParserManager.instance().getParser(annotationClass);
                            View view = annotationParser.createView(context, fieldValue, annotation);
                            if (view != null) {
                                childView = view;
                                break;
                            }
                        }
                    }

                    if (childView != null) {
                        childViews.add(new ViewWrapper(childView, order));
                    }
                }
            }

            Collections.sort(childViews, new Comparator<ViewWrapper>() {
                @Override
                public int compare(ViewWrapper o1, ViewWrapper o2) {
                    return o1.order < o2.order ? -1 : 1;
                }
            });

            for (ViewWrapper viewWrapper : childViews) {
                layoutFactory.addView(viewGroup, viewWrapper.view);
            }

            return viewGroup;
        }

    }


    class ViewWrapper {
        View view;
        int order;

        ViewWrapper(View view, int order) {
            this.view = view;
            this.order = order;
        }
    }

}

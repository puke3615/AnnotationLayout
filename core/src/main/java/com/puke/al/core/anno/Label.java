package com.puke.al.core.anno;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.puke.al.core.AnnotationParser;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zijiao
 * @version 17/10/16
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Viewable(Label.LabelParser.class)
public @interface Label {

    String value() default "";

    class LabelParser implements AnnotationParser {

        @Override
        public View createView(Context context, Object data, Annotation annotation) throws Exception {
            if (annotation instanceof Label) {
                return newLabel(context, data, ((Label) annotation));
            }
            return null;
        }

        @NonNull
        private View newLabel(Context context, Object data, Label label) {
            String format = label.value();
            TextView textView = new TextView(context);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            textView.setTextColor(Color.parseColor("#333333"));
            if (TextUtils.isEmpty(format)) {
                textView.setText(String.valueOf(data));
            } else {
                textView.setText(String.format(format, data));
            }
            return textView;
        }

    }

}

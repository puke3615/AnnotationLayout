package com.puke.al.core.anno;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

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
@Viewable(Button.ButtonParser.class)
public @interface Button {

    String value() default "";

    class ButtonParser implements AnnotationParser {

        @Override
        public View createView(Context context, Object data, Annotation annotation) throws Exception {
            if (annotation instanceof Button) {
                return newButton(context, data, ((Button) annotation));
            }
            return null;
        }

        @NonNull
        private View newButton(Context context, Object data, Button button) {
            String format = button.value();
            android.widget.Button btn = new android.widget.Button(context);
            if (TextUtils.isEmpty(format)) {
                btn.setText(String.valueOf(data));
            } else {
                btn.setText(String.format(format, data));
            }
            return btn;
        }

    }

}

package com.puke.al.core;

import com.puke.al.core.anno.Viewable;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zijiao
 * @version 17/10/16
 */
public class AnnotationParserManager {

    private static final byte[] sInstanceLock = new byte[0];
    private static AnnotationParserManager sInstance;
    private final Map<Class, AnnotationParser> mParsers;

    private AnnotationParserManager() {
        mParsers = new HashMap<>();
    }

    public static AnnotationParserManager instance() {
        if (sInstance == null) {
            synchronized (sInstanceLock) {
                if (sInstance == null) {
                    sInstance = new AnnotationParserManager();
                }
            }
        }
        return sInstance;
    }

    public AnnotationParser getParser(Class<? extends Annotation> annotationClass) throws Exception {
        AnnotationParser annotationParser = mParsers.get(annotationClass);
        if (annotationParser == null) {
            Viewable viewable = annotationClass.getAnnotation(Viewable.class);
            Class<? extends AnnotationParser> parserType = viewable.value();
            annotationParser = parserType.newInstance();
            mParsers.put(annotationClass, annotationParser);
        }
        return annotationParser;
    }


}

package com.github.syr0ws.settings.common.filter;

import com.github.syr0ws.settings.api.exception.SettingException;
import com.github.syr0ws.settings.api.filter.SettingFilter;
import com.github.syr0ws.settings.api.filter.SettingFilterFactory;
import com.github.syr0ws.settings.api.filter.annotation.*;
import com.github.syr0ws.settings.common.filter.type.MaxFilter;
import com.github.syr0ws.settings.common.filter.type.MinFilter;
import com.github.syr0ws.settings.common.filter.type.NotEmptyFilter;
import com.github.syr0ws.settings.common.filter.type.RangeFilter;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class SimpleSettingFilterFactory implements SettingFilterFactory {

    private static final Map<Class<? extends Annotation>, Function<Annotation, SettingFilter<?>>> ANNOTATION_FILTERS = new HashMap<>();

    static {

        // This enables to easily add filters without modifying a lot the existing code.
        ANNOTATION_FILTERS.put(NotEmpty.class, annotation -> new NotEmptyFilter());
        ANNOTATION_FILTERS.put(Min.class, annotation -> new MinFilter((Min) annotation));
        ANNOTATION_FILTERS.put(Max.class, annotation -> new MaxFilter((Max) annotation));
        ANNOTATION_FILTERS.put(Positive.class, annotation -> new MinFilter(0));
        ANNOTATION_FILTERS.put(Negative.class, annotation -> new MaxFilter(0));
        ANNOTATION_FILTERS.put(Range.class, annotation -> new RangeFilter((Range) annotation));
    }

    @Override
    public SettingFilter<?> getFilter(Annotation annotation) {

        Class<? extends Annotation> type = annotation.annotationType();

        if(!this.isSupported(annotation))
            throw new SettingException("Annotation not supported.");

        Function<Annotation, SettingFilter<?>> converter = ANNOTATION_FILTERS.get(type);

        return converter.apply(annotation);
    }

    @Override
    public boolean isSupported(Annotation annotation) {
        return ANNOTATION_FILTERS.containsKey(annotation.annotationType());
    }

    public static void addAnnotationFilter(Class<? extends Annotation> annotation, Function<Annotation, SettingFilter<?>> converter) {

        if(annotation == null)
            throw new IllegalArgumentException("Annotation type cannot be null.");

        if(converter == null)
            throw new IllegalArgumentException("Converter cannot be null.");

        ANNOTATION_FILTERS.put(annotation, converter);
    }
}

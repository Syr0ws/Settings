package com.github.syr0ws.settings.api.filter;

import java.lang.annotation.Annotation;

public interface SettingFilterFactory {

    SettingFilter<?> getFilter(Annotation annotation);

    boolean isSupported(Annotation annotation);
}

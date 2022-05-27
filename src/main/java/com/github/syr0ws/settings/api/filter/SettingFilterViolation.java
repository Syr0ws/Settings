package com.github.syr0ws.settings.api.filter;

public interface SettingFilterViolation<T> {

    T getValue();

    String getMessage();
}

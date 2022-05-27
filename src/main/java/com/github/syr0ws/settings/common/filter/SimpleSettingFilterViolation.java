package com.github.syr0ws.settings.common.filter;

import com.github.syr0ws.settings.api.filter.SettingFilterViolation;

public class SimpleSettingFilterViolation<T> implements SettingFilterViolation<T> {

    private final T value;
    private final String message;

    public SimpleSettingFilterViolation(T value, String message) {

        if(message == null)
            throw new IllegalArgumentException("Message cannot be null.");

        this.value = value;
        this.message = message;
    }

    public T getValue() {
        return this.value;
    }

    public String getMessage() {
        return this.message;
    }
}

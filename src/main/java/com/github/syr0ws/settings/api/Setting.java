package com.github.syr0ws.settings.api;

public interface Setting<T> {

    String getName();

    T getValue();

    boolean setValue(T value);
}

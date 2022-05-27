package com.github.syr0ws.settings.sdk;

import com.github.syr0ws.settings.api.Setting;

public class SimpleSetting<T> implements Setting<T> {

    private final String name;
    private T value;

    public SimpleSetting(String name, T value) {

        if(name == null)
            throw new IllegalArgumentException("Name cannot be null.");

        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public T getValue() {
        return this.value;
    }

    @Override
    public boolean setValue(T value) {
        this.value = value;
        return true;
    }
}

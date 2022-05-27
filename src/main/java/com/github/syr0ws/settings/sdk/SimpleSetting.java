package com.github.syr0ws.settings.sdk;

import com.github.syr0ws.settings.api.Setting;

public class SimpleSetting<T> implements Setting<T> {

    private final String name;
    private final Class<T> type;
    private T value;

    public SimpleSetting(String name, T value, Class<T> type) {

        if(name == null)
            throw new IllegalArgumentException("Name cannot be null.");

        if(type == null)
            throw new IllegalArgumentException("Type cannot be null.");

        this.name = name;
        this.type = type;

        this.value = value; // TODO To remove.
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

    @Override
    public Class<T> getValueType() {
        return this.type;
    }
}

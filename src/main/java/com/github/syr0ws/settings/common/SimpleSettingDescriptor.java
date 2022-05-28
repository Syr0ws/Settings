package com.github.syr0ws.settings.common;

import com.github.syr0ws.settings.api.Setting;
import com.github.syr0ws.settings.api.SettingDescriptor;

public class SimpleSettingDescriptor<T> implements SettingDescriptor<T> {

    private final String path;
    private final Setting<T> setting;

    public SimpleSettingDescriptor(String path, Setting<T> setting) {

        if(setting == null)
            throw new IllegalArgumentException("Setting cannot be null.");

        this.path = path;
        this.setting = setting;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public boolean isConfigurable() {
        return this.path != null;
    }

    @Override
    public Setting<T> getSetting() {
        return this.setting;
    }
}

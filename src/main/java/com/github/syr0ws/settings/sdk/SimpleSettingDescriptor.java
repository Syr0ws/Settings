package com.github.syr0ws.settings.sdk;

import com.github.syr0ws.settings.api.Setting;
import com.github.syr0ws.settings.api.SettingDescriptor;

public class SimpleSettingDescriptor<T> implements SettingDescriptor<T> {

    private final String path;
    private final Setting<T> setting;

    public SimpleSettingDescriptor(String path, Setting<T> setting) {
        this.path = path;
        this.setting = setting;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public Setting<T> getSetting() {
        return this.setting;
    }
}

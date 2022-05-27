package com.github.syr0ws.settings.api;

public interface SettingDescriptor<T> {

    String getPath();

    Setting<T> getSetting();
}

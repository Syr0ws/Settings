package com.github.syr0ws.settings.api;

public interface SettingDescriptor<T> {

    String getPath();

    boolean isConfigurable();

    Setting<T> getSetting();
}

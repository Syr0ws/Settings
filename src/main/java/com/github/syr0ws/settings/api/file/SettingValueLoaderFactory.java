package com.github.syr0ws.settings.api.file;

public interface SettingValueLoaderFactory {

    <T> SettingValueLoader<T> getLoader(Class<T> type);

    <T> boolean isSupported(Class<T> type);
}

package com.github.syr0ws.settings.api;

import com.github.syr0ws.settings.api.filter.SettingFilterManager;

public interface FilterableSetting<T> extends Setting<T> {

    SettingFilterManager<T> getFilterManager();
}

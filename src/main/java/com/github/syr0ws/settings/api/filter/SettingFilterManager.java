package com.github.syr0ws.settings.api.filter;

import java.util.Set;

public interface SettingFilterManager<T> {

    Set<SettingFilterViolation<T>> filter(T value);

    boolean addFilter(SettingFilter<T> filter);

    boolean removeFilter(SettingFilter<T> filter);

    Set<? extends SettingFilter<T>> getFilters();
}

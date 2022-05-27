package com.github.syr0ws.settings.api.filter;

import java.util.Optional;

public interface SettingFilter<T> {

    Optional<SettingFilterViolation<T>> filter(T value);

    Class<T> getValueType();
}

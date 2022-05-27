package com.github.syr0ws.settings.sdk.filter.type;

import com.github.syr0ws.settings.api.filter.SettingFilter;
import com.github.syr0ws.settings.api.filter.SettingFilterViolation;
import com.github.syr0ws.settings.sdk.filter.SimpleSettingFilterViolation;

import java.util.Optional;

public class NotNullFilter implements SettingFilter<Object> {

    @Override
    public Optional<SettingFilterViolation<Object>> filter(Object value) {

        if(value != null) return Optional.empty();

        String message = "Value cannot be null.";
        SimpleSettingFilterViolation<Object> violation = new SimpleSettingFilterViolation<>(null, message);

        return Optional.of(violation);
    }

    @Override
    public Class<Object> getValueType() {
        return Object.class;
    }
}

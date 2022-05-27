package com.github.syr0ws.settings.sdk.filter.type;

import com.github.syr0ws.settings.api.filter.SettingFilter;
import com.github.syr0ws.settings.api.filter.SettingFilterViolation;
import com.github.syr0ws.settings.sdk.filter.SimpleSettingFilterViolation;

import java.util.Optional;

public class NotEmptyFilter implements SettingFilter<String> {

    @Override
    public Optional<SettingFilterViolation<String>> filter(String value) {

        if(!value.isEmpty()) return Optional.empty();

        String message = "Value cannot be empty.";
        SimpleSettingFilterViolation<String> violation = new SimpleSettingFilterViolation<>(value, message);

        return Optional.of(violation);
    }

    @Override
    public Class<String> getValueType() {
        return String.class;
    }
}

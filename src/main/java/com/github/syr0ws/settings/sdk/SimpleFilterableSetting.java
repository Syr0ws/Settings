package com.github.syr0ws.settings.sdk;

import com.github.syr0ws.settings.api.FilterableSetting;
import com.github.syr0ws.settings.api.filter.SettingFilterManager;
import com.github.syr0ws.settings.api.filter.SettingFilterViolation;
import com.github.syr0ws.settings.sdk.filter.SimpleSettingFilterManager;

import java.util.Set;

public class SimpleFilterableSetting<T> extends SimpleSetting<T> implements FilterableSetting<T> {

    private final Class<T> type;
    private final SettingFilterManager<T> manager;

    public SimpleFilterableSetting(String name, T value, Class<T> type) {
        super(name, value);
        this.type = type;
        this.manager = new SimpleSettingFilterManager<>();
    }

    @Override
    public boolean setValue(T value) {

        Set<? extends SettingFilterViolation<T>> violations = this.manager.filter(value);

        if(violations.size() == 0) return super.setValue(value);

        // If there are violations, throwing handled exceptions to
        // avoid unattended behaviors. The calling code will still
        // work as the exceptions are handled here and the default
        // value kept.

        violations.forEach(violation -> {

            try { throw new IllegalArgumentException(violation.getMessage());
            } catch (IllegalArgumentException exception) { exception.printStackTrace();}
        });

        return false;
    }

    @Override
    public SettingFilterManager<T> getFilterManager() {
        return this.manager;
    }

    @Override
    public Class<T> getValueType() {
        return this.type;
    }
}

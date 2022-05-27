package com.github.syr0ws.settings.common;

import com.github.syr0ws.settings.api.FilterableSetting;
import com.github.syr0ws.settings.api.filter.SettingFilterManager;
import com.github.syr0ws.settings.api.filter.SettingFilterViolation;
import com.github.syr0ws.settings.common.filter.SimpleSettingFilterManager;

import java.util.Set;

public class SimpleFilterableSetting<T> extends SimpleSetting<T> implements FilterableSetting<T> {

    private final SettingFilterManager<T> manager;

    public SimpleFilterableSetting(String name, Class<T> type) {
        super(name, type);
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
}

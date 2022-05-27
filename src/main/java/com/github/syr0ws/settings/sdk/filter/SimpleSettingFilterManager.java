package com.github.syr0ws.settings.sdk.filter;

import com.github.syr0ws.settings.api.filter.SettingFilter;
import com.github.syr0ws.settings.api.filter.SettingFilterManager;
import com.github.syr0ws.settings.api.filter.SettingFilterViolation;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class SimpleSettingFilterManager<T> implements SettingFilterManager<T> {

    private final Set<SettingFilter<T>> filters = new HashSet<>();

    @Override
    public Set<SettingFilterViolation<T>> filter(T value) {
        return this.filters.stream()
                .map(filter -> filter.filter(value))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean addFilter(SettingFilter<T> filter) {

        if(filter == null)
            throw new IllegalArgumentException("SettingFilter cannot be null.");

        return this.filters.add(filter);
    }

    @Override
    public boolean removeFilter(SettingFilter<T> filter) {

        if(filter == null)
            throw new IllegalArgumentException("SettingFilter cannot be null.");

        return this.filters.remove(filter);
    }

    @Override
    public Set<? extends SettingFilter<T>> getFilters() {
        return Collections.unmodifiableSet(this.filters);
    }
}

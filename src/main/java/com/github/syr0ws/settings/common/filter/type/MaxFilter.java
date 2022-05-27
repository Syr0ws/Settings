package com.github.syr0ws.settings.common.filter.type;

import com.github.syr0ws.settings.api.filter.SettingFilterViolation;
import com.github.syr0ws.settings.api.filter.annotation.Max;
import com.github.syr0ws.settings.common.filter.SimpleSettingFilterViolation;

import java.util.Optional;

public class MaxFilter extends NumberFilter {

    private final int reference;

    public MaxFilter(int reference) {
        this.reference = reference;
    }

    public MaxFilter(Max min) {
        this.reference = min.value();
    }

    @Override
    public Optional<SettingFilterViolation<Number>> filter(Number value) {

        int result = super.compare(this.reference, value);

        if(result >= 0) return Optional.empty();

        String message = String.format("Value must be lower than %d (current : %s).", this.reference, value);
        SettingFilterViolation<Number> violation = new SimpleSettingFilterViolation<>(value, message);

        return Optional.of(violation);
    }
}

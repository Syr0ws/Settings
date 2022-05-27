package com.github.syr0ws.settings.common.filter.type;

import com.github.syr0ws.settings.api.filter.SettingFilterViolation;
import com.github.syr0ws.settings.api.filter.annotation.Min;
import com.github.syr0ws.settings.common.filter.SimpleSettingFilterViolation;

import java.util.Optional;

public class MinFilter extends NumberFilter {

    private final int reference;

    public MinFilter(int reference) {
        this.reference = reference;
    }

    public MinFilter(Min min) {
        this.reference = min.value();
    }

    @Override
    public Optional<SettingFilterViolation<Number>> filter(Number value) {

        int result = super.compare(this.reference, value);

        if(result <= 0) return Optional.empty();

        String message = String.format("Value must be greater than %d (current : %s).", this.reference, value);
        SettingFilterViolation<Number> violation = new SimpleSettingFilterViolation<>(value, message);

        return Optional.of(violation);
    }
}

package com.github.syr0ws.settings.sdk.filter.type;

import com.github.syr0ws.settings.api.filter.SettingFilterViolation;
import com.github.syr0ws.settings.api.filter.annotation.Range;
import com.github.syr0ws.settings.sdk.filter.SimpleSettingFilterViolation;

import java.util.Optional;

public class RangeFilter extends NumberFilter {

    private final int min, max;

    public RangeFilter(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public RangeFilter(Range range) {
        this.min = range.min();
        this.max = range.max();
    }

    @Override
    public Optional<SettingFilterViolation<Number>> filter(Number value) {

        int result1 = super.compare(this.min, value);
        int result2 = super.compare(this.max, value);

        if(result1 <= 0 && result2 >= 0) return Optional.empty();

        String message = String.format("Value must be between %d and %d (current : %s).", this.min, this.max, value);
        SettingFilterViolation<Number> violation = new SimpleSettingFilterViolation<>(value, message);

        return Optional.of(violation);
    }
}
